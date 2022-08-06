package com.sebastianb.myapplication.ui.signup

import android.util.Log
import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sebastianb.myapplication.databinding.FragmentSignUpBinding

class SignUpViewModel : ViewModel() {


    private val _errormsg: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String> = _errormsg
    fun validateFields(telefono:String,name:String,email: String, password: String, passwordAgain: String): Int {
        var next=0
        var p=0
        var t=0
        if (password == passwordAgain) {
            Log.d("check", "true")
            next+=1
        }
        else{
            next=0
            p=1
        }

        if (name.isNotEmpty()) {
            Log.d("check", "true")
            next+=1
        }
        else{
            _errormsg.value = "El nombre no puede estar vacio"
            t+=1
            next=0
        }

        if (email.isNotEmpty()) {
            Log.d("check", "true")
            next+=1
        }
        else{
            _errormsg.value = "El correo electronico no puede estar vacio"
            t+=1
            next=0
        }
        if (telefono.isNotEmpty()) {
            Log.d("check", "true")
            next+=1
        }
        else{
            _errormsg.value = "El telefono no puede estar vacio"
            t+=1
            next=0
        }
        if (telefono.length>10) {
            Log.d("check", "true")
            next+=1
        }
        else{
            _errormsg.value = "El telefono debe contener al menos 10 digitos"

            next=0
        }
        if (password.isNotEmpty()) {
            Log.d("check", "true")
            next+=1
        }
        else{
            t+=1
            next=0
            p=2
        }
        if (password.length > 6) {
            Log.d("check", "true")
            next+=1

        }
        else{
            t+=1
            next=0
            p=3
        }
        if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            _errormsg.value = "Ingrese un correo electronico valido"
            next=0

        }
        if(p==1)_errormsg.value = "Las contraseñas deben ser iguales"
        if(p==2)_errormsg.value = "La contraseña no puede estar vacio"
        if(p==3)_errormsg.value = "La contraseña debe tener al menos 6 digitos"



        return next
    }

}