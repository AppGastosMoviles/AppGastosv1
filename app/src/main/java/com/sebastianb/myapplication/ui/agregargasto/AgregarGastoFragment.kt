package com.sebastianb.myapplication.ui.agregargasto

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sebastianb.myapplication.R
import com.sebastianb.myapplication.databinding.FragmentAgregarGastoBinding
import java.util.*

class AgregarGastoFragment : Fragment() {

    private lateinit var agregarGastoBinding: FragmentAgregarGastoBinding
    private lateinit var agregarGastoViewModel: AgregarGastoViewModel


    private var fechanacimiento: String = ""
    private var cal = Calendar.getInstance()


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        agregarGastoViewModel = ViewModelProvider(this)[AgregarGastoViewModel::class.java]
        agregarGastoBinding = FragmentAgregarGastoBinding.inflate(inflater, container, false)
        agregarGastoViewModel.errorMsg.observe(viewLifecycleOwner) { msg ->
            showErrorMessage(msg)
        }

        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val formato = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            fechanacimiento = formato.format(cal.time).toString()
            agregarGastoBinding.dateEditText.setText(fechanacimiento)
        }

        agregarGastoBinding.dateEditText.setOnClickListener {

            DatePickerDialog(
                requireContext(),
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        val categorias = resources.getStringArray(R.array.lista_categorias)

        //Creación del adapter
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, categorias)

        //Agregamos el adapter al autoCompleteTextView
        with(agregarGastoBinding.atvCategorias) {
            setAdapter(adapter)
        }

        agregarGastoBinding.btnAgregarGasto.setOnClickListener {
            agregarGastoViewModel.validateFields(
                agregarGastoBinding.etDescripcion.text.toString(),
                agregarGastoBinding.etGasto.text.toString(),
                agregarGastoBinding.establecimientoEditText.text.toString(),
                agregarGastoBinding.atvCategorias.text.toString(),
                agregarGastoBinding.dateEditText.text.toString()
            )
            goToLogin(
                agregarGastoViewModel.validateFields(
                    agregarGastoBinding.etDescripcion.text.toString(),
                    agregarGastoBinding.etGasto.text.toString(),
                    agregarGastoBinding.establecimientoEditText.text.toString(),
                    agregarGastoBinding.atvCategorias.text.toString(),
                    agregarGastoBinding.dateEditText.text.toString()
                )
            )

        }
        return agregarGastoBinding.root
    }

    private fun showErrorMessage(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
    }

    fun goToLogin(next: Int) {
        if (next == 5)
            findNavController().navigate(AgregarGastoFragmentDirections.actionAgregarGastoFragmentToNavigationHome())

    }

}