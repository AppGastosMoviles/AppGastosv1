package com.sebastianb.myapplication.ui.categoria

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sebastianb.myapplication.databinding.FragmentCategoriaBinding
import com.sebastianb.myapplication.databinding.FragmentSignUpBinding
import com.sebastianb.myapplication.ui.signup.SignUpFragmentDirections
import com.sebastianb.myapplication.ui.signup.SignUpViewModel


class CategoriaFragment : Fragment() {

    private lateinit var categoriaBinding: FragmentCategoriaBinding

    private lateinit var categoriaViewModel: CategoriaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        categoriaBinding = FragmentCategoriaBinding.inflate(inflater, container, false)
        categoriaViewModel = ViewModelProvider(this)[CategoriaViewModel::class.java]

        categoriaBinding.alimentacionButton.setOnClickListener {
            findNavController().navigate(CategoriaFragmentDirections.actionNavigationCategoriaToAlimentacionFragment())
        }

        categoriaBinding.indumentariaButton.setOnClickListener {
            findNavController().navigate(CategoriaFragmentDirections.actionNavigationCategoriaToIndumentariaFragment())
        }

        categoriaBinding.cuidadopersonalButton.setOnClickListener {
            findNavController().navigate(CategoriaFragmentDirections.actionNavigationCategoriaToCuidadopersonalFragment())
        }

        categoriaBinding.entretenimientoButton.setOnClickListener {
            findNavController().navigate(CategoriaFragmentDirections.actionNavigationCategoriaToEntretenimientoFragment())
        }

        categoriaBinding.otrosButton.setOnClickListener {
            findNavController().navigate(CategoriaFragmentDirections.actionNavigationCategoriaToOtrosFragment())
        }

        return categoriaBinding.root
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

}