package com.sebastianb.myapplication.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sebastianb.myapplication.databinding.FragmentLoginBinding
import com.sebastianb.myapplication.databinding.FragmentSignUpBinding
import com.sebastianb.myapplication.ui.signup.SignUpViewModel


class LoginFragment : Fragment() {

    private lateinit var loginBinding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]


        loginViewModel.errorMsg.observe(viewLifecycleOwner) { msg ->
            showErrorMessage(msg)

        }

        loginBinding.loginButton.setOnClickListener{
            if (loginViewModel.validateFields(loginBinding.emailEditText.text.toString(),loginBinding.passwordEditText.text.toString())==3){
            findNavController().navigate(LoginFragmentDirections.actionNavigationLoginToNavigationList())}

        }
        loginBinding.registersButton.setOnClickListener{
            findNavController().navigate(LoginFragmentDirections.actionNavigationLoginToNavigationSingup())
        }
        return loginBinding.root
    }
    private fun showErrorMessage(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

}