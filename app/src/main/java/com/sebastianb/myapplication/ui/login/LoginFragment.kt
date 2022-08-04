package com.sebastianb.myapplication.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sebastianb.myapplication.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var loginBinding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        loginBinding.loginButton.setOnClickListener{
            findNavController().navigate(LoginFragmentDirections.actionNavigationLoginToNavigationList())

        }
        loginBinding.registersButton.setOnClickListener{
            findNavController().navigate(LoginFragmentDirections.actionNavigationLoginToNavigationSingup())
        }
        return loginBinding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

}