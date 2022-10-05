package com.sebastianb.myapplication.ui.perfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
            goToPerfil()
        }

        perfilViewModel.validateNameData()
        perfilViewModel.userNameData.observe(viewLifecycleOwner) { usernameData ->
            perfilBinding.textViewname.text = usernameData
        }

        perfilViewModel.validateEmailData()
        perfilViewModel.userEmailData.observe(viewLifecycleOwner) { useremailData ->
            perfilBinding.textViewemail.text = useremailData
        }

        perfilViewModel.validatePhoneData()
        perfilViewModel.userPhoneData.observe(viewLifecycleOwner) { userphoneData ->
            perfilBinding.textViewphone.text = userphoneData
        }


        perfilBinding.cerrarButton.setOnClickListener {
            perfilViewModel.logOut()
            goToLogin()
        }


        return perfilBinding.root
    }

    fun goToLogin() {
        findNavController().navigate(PerfilFragmentDirections.actionNavigationCuentaToNavigationLogin())
    }

    fun goToPerfil() {
        findNavController().navigate(PerfilFragmentDirections.actionNavigationCuentaToEditarperfilFragment())
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

}
