package com.sebastianb.myapplication.ui.alimentacion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sebastianb.myapplication.R

class AlimentacionFragment : Fragment() {

    companion object {
        fun newInstance() = AlimentacionFragment()
    }

    private lateinit var viewModel: AlimentacionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_alimentacion, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AlimentacionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}