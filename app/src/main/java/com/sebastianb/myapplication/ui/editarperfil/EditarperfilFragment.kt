package com.sebastianb.myapplication.ui.editarperfil

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.sebastianb.myapplication.databinding.FragmentEditarperfilBinding


class EditarperfilFragment : Fragment() {
    private lateinit var editarperfilBinding: FragmentEditarperfilBinding
    private lateinit var editarperfilViewModel: EditarperfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        editarperfilBinding = FragmentEditarperfilBinding.inflate(inflater, container, false)
        editarperfilViewModel = ViewModelProvider(this)[EditarperfilViewModel::class.java]

        editarperfilViewModel.errorMsg.observe(viewLifecycleOwner) { msg ->
            showErrorMessage(msg)

        }
        with(editarperfilBinding) {
            guardarButton.setOnClickListener {
                editarperfilViewModel.validateFields(
                    phoneEditText.text.toString(),
                    nameEditText.text.toString(),
                    emailEditText.text.toString(),
                    passwordEditText.text.toString(),
                )

            }
            return editarperfilBinding.root
        }
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }
    private fun showErrorMessage(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
    }
    fun goToLogin(next: Int) {
        if (next ==6)
            findNavController().navigate(EditarperfilFragmentDirections.actionEditarperfilFragmentToNavigationCuenta())

    }
}
