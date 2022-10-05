package com.sebastianb.myapplication.ui.indumentaria

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sebastianb.myapplication.data.GastoRepository
import com.sebastianb.myapplication.databinding.FragmentIndumentariaBinding
import com.sebastianb.myapplication.model.Gasto
import com.sebastianb.myapplication.ui.gastosrecientes.GastosAdapter

class IndumentariaFragment : Fragment() {

    private lateinit var indumentariaBinding: FragmentIndumentariaBinding
    private lateinit var indumentariaViewModel: IndumentariaViewModel
    private lateinit var gastosAdapter: GastosAdapter
    private var gastoList:ArrayList<Gasto> = ArrayList()
    private val gastoRepository = GastoRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        indumentariaBinding=FragmentIndumentariaBinding.inflate(inflater,container,false)
        indumentariaViewModel=ViewModelProvider(this)[IndumentariaViewModel::class.java]

        indumentariaViewModel.loadGastos()
        indumentariaViewModel.gastosList.observe(viewLifecycleOwner){ gastoList ->
            gastosAdapter.appendItems(gastoList)
            gastoList.clear()

        }
        gastosAdapter=GastosAdapter(gastoList,deleteClicked = { gasto ->
            gastoRepository.deleteGasto(gasto)
            Toast.makeText(context,"Gasto eliminado!", Toast.LENGTH_LONG).show()
            indumentariaViewModel.loadGastos()
            indumentariaBinding.gastosRecyclerView.apply {
                layoutManager= LinearLayoutManager(this@IndumentariaFragment.requireContext())
                adapter=gastosAdapter
                setHasFixedSize(false)
            }
        })
        indumentariaBinding.gastosRecyclerView.apply {
            layoutManager= LinearLayoutManager(this@IndumentariaFragment.requireContext())
            adapter=gastosAdapter
            setHasFixedSize(false)
        }
        return indumentariaBinding.root
    }



}