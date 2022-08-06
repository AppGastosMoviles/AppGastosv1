package com.sebastianb.myapplication.ui.login

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _errormsg: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String> = _errormsg
    fun validateFields(email: String, password: String): Int {
        var next=0
        if (password.isNotEmpty()) next+=1
        else {
            _errormsg.value = "La contraseña no puede estar vacia"
            next = 0
        }
        if(email.isNotEmpty())next+=1
        else {
            _errormsg.value = "El correo electronico no puede estar vacio"
            next=0
        }
        if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            _errormsg.value = "Ingrese un correo electronico valido"
            next=0
        }
        if (password.length>6)next+=1
        else{
            _errormsg.value = "La contraseña debe tener al menos 6 digitos"
            next=0
        }

        return next
    }
}