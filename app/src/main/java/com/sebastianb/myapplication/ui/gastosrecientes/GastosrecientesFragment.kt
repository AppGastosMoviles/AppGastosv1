package com.sebastianb.myapplication.ui.gastosrecientes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sebastianb.myapplication.databinding.FragmentGastosrecientesBinding


class GastosrecientesFragment : Fragment() {

    private lateinit var gastosrecientesBinding: FragmentGastosrecientesBinding
    private lateinit var gastosrecientesViewModel: GastosrecientesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        gastosrecientesBinding=FragmentGastosrecientesBinding.inflate(inflater,container,false)
        gastosrecientesViewModel=ViewModelProvider(this)[GastosrecientesViewModel::class.java]
        return gastosrecientesBinding.root
    }


}