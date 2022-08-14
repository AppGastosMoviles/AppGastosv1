package com.sebastianb.myapplication.ui.entretenimiento

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sebastianb.myapplication.databinding.FragmentEntretenimientoBinding

class EntretenimientoFragment : Fragment() {

    private lateinit var entretenimientoBinding: FragmentEntretenimientoBinding
    private lateinit var entretenimientoViewModel: EntretenimientoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        entretenimientoBinding=FragmentEntretenimientoBinding.inflate(inflater,container,false)
        entretenimientoViewModel=ViewModelProvider(this)[EntretenimientoViewModel::class.java]
        return entretenimientoBinding.root
    }



}