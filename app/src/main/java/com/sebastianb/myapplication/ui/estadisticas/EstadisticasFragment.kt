package com.sebastianb.myapplication.ui.estadisticas

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sebastianb.myapplication.R

class EstadisticasFragment : Fragment() {

    companion object {
        fun newInstance() = EstadisticasFragment()
    }

    private lateinit var viewModel: EstadisticasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_estadisticas, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EstadisticasViewModel::class.java)
        // TODO: Use the ViewModel
    }

}