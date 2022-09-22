package com.sebastianb.myapplication.ui.login

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sebastianb.myapplication.data.ResourceRemote
import com.sebastianb.myapplication.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val userRepository = UserRepository()
    private val _errormsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errormsg
    private val _loginSuccess:MutableLiveData<String?> = MutableLiveData()
    val loginSuccess: LiveData<String?> = _loginSuccess

    fun validateFields(email: String, password: String) {
        if(email.isEmpty()||password.isEmpty())
            _errormsg.value = "Ingrese todos los campos."
        else if(!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches())
            _errormsg.value = "Ingrese un correo electronico valido"
        else if (password.length<5)
            _errormsg.value = "La contraseña debe tener al menos 6 digitos"
        else
            GlobalScope.launch(Dispatchers.IO) {
                val result=userRepository.loginUser(email,password)
                result.let { resourceRemote ->
                    when (resourceRemote) {
                        is ResourceRemote.Succes -> {
                            _loginSuccess.postValue(result.data)
                            _errormsg.postValue("Bienvenido!")
                        }
                        is ResourceRemote.Error -> {
                            var msg = result.message
                            when (result.message) {
                                "The email address is badly formatted." -> msg =
                                    "El email esta mal escrito."
                                "A network error (such as timeout, interrupted connection or unreachable host) has ocurred." -> msg =
                                    "No tiene conexion a internet"
                                "There is no user record corresponding to this identifier. The user may have been deleted." -> msg=
                                    "No existe una cuenta con este correo."
                                "The password is invalid or the user does not have a password." ->msg=
                                    "La contraseña no coincide con el correo"
                            }
                            _errormsg.postValue(msg)

                        }
                        else -> {
                            //dont use
                        }
                    }
            }
    }

}
}