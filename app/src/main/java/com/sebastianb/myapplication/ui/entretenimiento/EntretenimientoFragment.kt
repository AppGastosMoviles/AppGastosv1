package com.sebastianb.myapplication.ui.entretenimiento

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sebastianb.myapplication.data.GastoRepository
import com.sebastianb.myapplication.databinding.FragmentEntretenimientoBinding
import com.sebastianb.myapplication.model.Gasto
import com.sebastianb.myapplication.ui.gastosrecientes.GastosAdapter

class EntretenimientoFragment : Fragment() {

    private lateinit var entretenimientoBinding: FragmentEntretenimientoBinding
    private lateinit var entretenimientoViewModel: EntretenimientoViewModel
    private lateinit var gastosAdapter: GastosAdapter
    private var gastoList:ArrayList<Gasto> = ArrayList()
    private val gastoRepository = GastoRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        entretenimientoBinding=FragmentEntretenimientoBinding.inflate(inflater,container,false)
        entretenimientoViewModel=ViewModelProvider(this)[EntretenimientoViewModel::class.java]

        entretenimientoViewModel.loadGastos()
        entretenimientoViewModel.gastosList.observe(viewLifecycleOwner){ gastoList ->
            gastosAdapter.appendItems(gastoList)
            gastoList.clear()

        }
        gastosAdapter=GastosAdapter(gastoList,deleteClicked = { gasto ->
            gastoRepository.deleteGasto(gasto)
            Toast.makeText(context,"Gasto eliminado!", Toast.LENGTH_LONG).show()
            entretenimientoViewModel.loadGastos()
            entretenimientoBinding.gastosRecyclerView.apply {
                layoutManager= LinearLayoutManager(this@EntretenimientoFragment.requireContext())
                adapter=gastosAdapter
                setHasFixedSize(false)
            }

        })
        entretenimientoBinding.gastosRecyclerView.apply {
            layoutManager= LinearLayoutManager(this@EntretenimientoFragment.requireContext())
            adapter=gastosAdapter
            setHasFixedSize(false)
        }
        return entretenimientoBinding.root
    }



}