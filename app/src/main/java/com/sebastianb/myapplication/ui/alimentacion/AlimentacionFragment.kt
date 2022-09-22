package com.sebastianb.myapplication.ui.alimentacion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sebastianb.myapplication.databinding.FragmentAlimentacionBinding
import com.sebastianb.myapplication.model.Gasto
import com.sebastianb.myapplication.ui.gastosrecientes.GastosAdapter


class AlimentacionFragment : Fragment() {

    private lateinit var alimentacionBinding: FragmentAlimentacionBinding
    private lateinit var alimentacionViewModel: AlimentacionViewModel
    private lateinit var gastosAdapter: GastosAdapter
    private var gastoList:ArrayList<Gasto> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        alimentacionBinding=FragmentAlimentacionBinding.inflate(inflater,container,false)
        alimentacionViewModel=ViewModelProvider(this)[AlimentacionViewModel::class.java]

        alimentacionViewModel.loadGastos()
        alimentacionViewModel.gastosList.observe(viewLifecycleOwner){ gastoList ->
            gastosAdapter.appendItems(gastoList)
            gastoList.clear()

        }
        gastosAdapter=GastosAdapter(gastoList)
        alimentacionBinding.gastosRecyclerView.apply {
            layoutManager= LinearLayoutManager(this@AlimentacionFragment.requireContext())
            adapter=gastosAdapter
            setHasFixedSize(false)
        }
        return alimentacionBinding.root
    }



}