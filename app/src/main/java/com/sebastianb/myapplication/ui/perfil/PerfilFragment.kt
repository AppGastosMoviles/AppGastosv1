package com.sebastianb.myapplication.ui.perfil

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.sebastianb.myapplication.databinding.FragmentPerfilBinding


class PerfilFragment : Fragment() {

    private lateinit var perfilBinding: FragmentPerfilBinding
    private lateinit var perfilViewModel: PerfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        perfilBinding = FragmentPerfilBinding.inflate(inflater, container, false)
        perfilViewModel = ViewModelProvider(this)[PerfilViewModel::class.java]

        perfilBinding.editarButton.setOnClickListener {
            findNavController().navigate(PerfilFragmentDirections.actionNavigationCuentaToEditarperfilFragment())
        }
        perfilBinding.cerrarButton.setOnClickListener {
            findNavController().navigate(PerfilFragmentDirections.actionNavigationCuentaToNavigationLogin())
        }
        return perfilBinding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

}
