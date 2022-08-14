package com.sebastianb.myapplication.ui.alimentacion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sebastianb.myapplication.databinding.FragmentAlimentacionBinding


class AlimentacionFragment : Fragment() {

    private lateinit var alimentacionBinding: FragmentAlimentacionBinding
    private lateinit var alimentacionViewModel: AlimentacionViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        alimentacionBinding=FragmentAlimentacionBinding.inflate(inflater,container,false)
        alimentacionViewModel=ViewModelProvider(this)[AlimentacionViewModel::class.java]
        return alimentacionBinding.root
    }



}