package com.sebastianb.myapplication.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sebastianb.myapplication.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {

    private lateinit var signUpBinding: FragmentSignUpBinding

    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        signUpBinding = FragmentSignUpBinding.inflate(inflater, container, false)
        signUpViewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        signUpBinding.registerButton.setOnClickListener {
            findNavController().navigate(SignUpFragmentDirections.actionNavigationSingupToNavigationLogin())
        }
        return signUpBinding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }
}