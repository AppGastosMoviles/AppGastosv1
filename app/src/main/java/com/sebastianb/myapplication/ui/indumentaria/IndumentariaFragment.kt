package com.sebastianb.myapplication.ui.indumentaria

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sebastianb.myapplication.databinding.FragmentIndumentariaBinding

class IndumentariaFragment : Fragment() {

    private lateinit var indumentariaBinding: FragmentIndumentariaBinding
    private lateinit var indumentariaViewModel: IndumentariaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        indumentariaBinding=FragmentIndumentariaBinding.inflate(inflater,container,false)
        indumentariaViewModel=ViewModelProvider(this)[IndumentariaViewModel::class.java]
        return indumentariaBinding.root
    }



}