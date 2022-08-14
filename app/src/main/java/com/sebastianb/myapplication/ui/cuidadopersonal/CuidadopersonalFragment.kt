package com.sebastianb.myapplication.ui.cuidadopersonal

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sebastianb.myapplication.databinding.FragmentCuidadopersonalBinding

class CuidadopersonalFragment : Fragment() {

    private lateinit var cuidadopersonalBinding: FragmentCuidadopersonalBinding
    private lateinit var cuidadopersonalViewModel: CuidadopersonalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cuidadopersonalBinding=FragmentCuidadopersonalBinding.inflate(inflater,container,false)
        cuidadopersonalViewModel=ViewModelProvider(this)[CuidadopersonalViewModel::class.java]
        return cuidadopersonalBinding.root
    }



}