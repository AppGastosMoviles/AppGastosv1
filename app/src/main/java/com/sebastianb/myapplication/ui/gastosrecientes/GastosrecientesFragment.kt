package com.sebastianb.myapplication.ui.gastosrecientes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sebastianb.myapplication.data.GastoRepository
import com.sebastianb.myapplication.databinding.FragmentGastosrecientesBinding
import com.sebastianb.myapplication.model.Gasto
import com.sebastianb.myapplication.ui.agregargasto.AgregarGastoFragmentDirections


class GastosrecientesFragment : Fragment() {

    private lateinit var gastosrecientesBinding: FragmentGastosrecientesBinding
    private lateinit var gastosrecientesViewModel: GastosrecientesViewModel
    private lateinit var gastosAdapter:GastosAdapter
    private var gastoList:ArrayList<Gasto> = ArrayList()
    private val gastoRepository = GastoRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        gastosrecientesBinding=FragmentGastosrecientesBinding.inflate(inflater,container,false)
        gastosrecientesViewModel=ViewModelProvider(this)[GastosrecientesViewModel::class.java]

        gastosrecientesViewModel.loadGastos()
        gastosrecientesViewModel.gastosList.observe(viewLifecycleOwner){ gastoList ->
            gastosAdapter.appendItems(gastoList)
            gastoList.clear()

        }

        gastosAdapter=GastosAdapter(gastoList, deleteClicked = { gasto ->
            gastoRepository.deleteGasto(gasto)
            Toast.makeText(context,"Gasto eliminado!", Toast.LENGTH_LONG).show()

            gastosrecientesViewModel.loadGastos()
            gastosrecientesBinding.gastosRecyclerView.apply {
                layoutManager=LinearLayoutManager(this@GastosrecientesFragment.requireContext())
                adapter=gastosAdapter
                setHasFixedSize(false)
            }
        })
        gastosrecientesBinding.gastosRecyclerView.apply {
            layoutManager=LinearLayoutManager(this@GastosrecientesFragment.requireContext())
            adapter=gastosAdapter
            setHasFixedSize(false)
        }
/*
        gastosrecientesViewModel.gastoData.observe(viewLifecycleOwner){gastoData ->
            gastosrecientesBinding.resultTextView.text=gastoData

        }
        gastosrecientesViewModel.foundGasto.observe(viewLifecycleOwner){ foundGasto ->
            gastosrecientesBinding.eliminarButton.visibility= if(foundGasto==true) View.VISIBLE else View.GONE

        }
        gastosrecientesBinding.eliminarButton.setOnClickListener{
            gastosrecientesViewModel.deleteGasto()
        }
        gastosrecientesViewModel.gastoDeleted.observe(viewLifecycleOwner){deletedGasto ->
            gastosrecientesBinding.eliminarButton.visibility= if(deletedGasto==true) View.GONE else View.VISIBLE
            gastosrecientesBinding.resultTextView.setText("")
        }
*/
        gastosrecientesViewModel.validateData()
        return gastosrecientesBinding.root
    }

}