package com.sebastianb.myapplication.ui.otros

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sebastianb.myapplication.databinding.FragmentOtrosBinding

class OtrosFragment : Fragment() {

   private lateinit var otrosBinding: FragmentOtrosBinding
   private lateinit var otrosViewModel: OtrosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        otrosBinding=FragmentOtrosBinding.inflate(inflater,container,false)
        otrosViewModel=ViewModelProvider(this)[OtrosViewModel::class.java]
        return otrosBinding.root
    }



}