package com.sebastianb.myapplication.ui.otros

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sebastianb.myapplication.databinding.FragmentOtrosBinding
import com.sebastianb.myapplication.model.Gasto
import com.sebastianb.myapplication.ui.gastosrecientes.GastosAdapter

class OtrosFragment : Fragment() {

   private lateinit var otrosBinding: FragmentOtrosBinding
   private lateinit var otrosViewModel: OtrosViewModel
    private lateinit var gastosAdapter: GastosAdapter
    private var gastoList:ArrayList<Gasto> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        otrosBinding=FragmentOtrosBinding.inflate(inflater,container,false)
        otrosViewModel=ViewModelProvider(this)[OtrosViewModel::class.java]

        otrosViewModel.loadGastos()
        otrosViewModel.gastosList.observe(viewLifecycleOwner){ gastoList ->
            gastosAdapter.appendItems(gastoList)
            gastoList.clear()

        }
        gastosAdapter=GastosAdapter(gastoList)
        otrosBinding.gastosRecyclerView.apply {
            layoutManager= LinearLayoutManager(this@OtrosFragment.requireContext())
            adapter=gastosAdapter
            setHasFixedSize(false)
        }
        return otrosBinding.root
    }



}