package com.sebastianb.myapplication.ui.agregargasto

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AgregarGastoViewModel : ViewModel() {


    private val _errormsg: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String> = _errormsg
    fun validateFields(
        descripcion: String,
        valor: String,
        establecimiento: String,
        categoria: String,
        fecha:String
    ): Int {
        var next = 0
        if (descripcion.isNotEmpty()) {
            Log.d("check", "true")
            next += 1
        } else {
            _errormsg.value = "La descripcion no puede estar vacio"
            next = 0
        }

        if (valor.isNotEmpty()) {
            Log.d("check", "true")
            next += 1
        } else {
            _errormsg.value = "El valor no puede estar vacio"
            next = 0
        }

        if (establecimiento.isNotEmpty()) {
            Log.d("check", "true")
            next += 1
        } else {
            _errormsg.value = "El Establecimiento no puede estar vacio"
            next = 0
        }
        if (categoria == "Alimentación" || categoria == "Indumentaria" || categoria == "Entretenimiento" || categoria == "Cuidado personal" || categoria == "Otros") {
            Log.d("check", "true")
            next += 1
        } else {
            _errormsg.value = "La categoria no puede estar vacia"
            next = 0
        }
        if (fecha.isNotEmpty()) {
            Log.d("check", "true")
            next += 1
        } else {
            _errormsg.value = "La fecha no puede estar vacia"
            next = 0
        }
        return next
    }
}
