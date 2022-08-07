package com.sebastianb.myapplication.ui.cuidadopersonal

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sebastianb.myapplication.R

class CuidadopersonalFragment : Fragment() {

    companion object {
        fun newInstance() = CuidadopersonalFragment()
    }

    private lateinit var viewModel: CuidadopersonalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cuidadopersonal, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CuidadopersonalViewModel::class.java)
        // TODO: Use the ViewModel
    }

}