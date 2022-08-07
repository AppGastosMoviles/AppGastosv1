package com.sebastianb.myapplication.ui.otros

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sebastianb.myapplication.R

class OtrosFragment : Fragment() {

    companion object {
        fun newInstance() = OtrosFragment()
    }

    private lateinit var viewModel: OtrosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_otros, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OtrosViewModel::class.java)
        // TODO: Use the ViewModel
    }

}