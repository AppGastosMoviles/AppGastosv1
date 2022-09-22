package com.sebastianb.myapplication.ui.cuidadopersonal

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sebastianb.myapplication.databinding.FragmentCuidadopersonalBinding
import com.sebastianb.myapplication.model.Gasto
import com.sebastianb.myapplication.ui.gastosrecientes.GastosAdapter

class CuidadopersonalFragment : Fragment() {

    private lateinit var cuidadopersonalBinding: FragmentCuidadopersonalBinding
    private lateinit var cuidadopersonalViewModel: CuidadopersonalViewModel
    private lateinit var gastosAdapter: GastosAdapter
    private var gastoList:ArrayList<Gasto> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cuidadopersonalBinding=FragmentCuidadopersonalBinding.inflate(inflater,container,false)
        cuidadopersonalViewModel=ViewModelProvider(this)[CuidadopersonalViewModel::class.java]

        cuidadopersonalViewModel.loadGastos()
        cuidadopersonalViewModel.gastosList.observe(viewLifecycleOwner){ gastoList ->
            gastosAdapter.appendItems(gastoList)
            gastoList.clear()

        }
        gastosAdapter= GastosAdapter(gastoList)
        cuidadopersonalBinding.gastosRecyclerView.apply {
            layoutManager= LinearLayoutManager(this@CuidadopersonalFragment.requireContext())
            adapter=gastosAdapter
            setHasFixedSize(false)
        }
        return cuidadopersonalBinding.root
    }



}