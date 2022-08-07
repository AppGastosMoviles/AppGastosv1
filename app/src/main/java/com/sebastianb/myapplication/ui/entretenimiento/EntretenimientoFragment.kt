package com.sebastianb.myapplication.ui.entretenimiento

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sebastianb.myapplication.R

class EntretenimientoFragment : Fragment() {

    companion object {
        fun newInstance() = EntretenimientoFragment()
    }

    private lateinit var viewModel: EntretenimientoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_entretenimiento, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EntretenimientoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}