package com.sebastianb.myapplication.ui.signup

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastianb.myapplication.data.ResourceRemote
import com.sebastianb.myapplication.data.UserRepository
import com.sebastianb.myapplication.model.User
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {


    private val userRepository = UserRepository()
    private val _errormsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errormsg

    private lateinit var user: User

    private val _registerSuccess: MutableLiveData<String?> = MutableLiveData()
    val registerSuccess: LiveData<String?> = _registerSuccess
    fun validateFields(
        telefono: String,
        name: String,
        email: String,
        password: String,
        passwordAgain: String
    ) {
        if (email.isEmpty() || password.isEmpty() || passwordAgain.isEmpty() || name.isEmpty() || telefono.isEmpty())
            _errormsg.value = "Debe ingresar todos los campos"
        else
            if (password != passwordAgain)
                _errormsg.value = "Las contraseñas deben ser iguales"
            else if (telefono.length != 10)
                _errormsg.value = "El telefono debe tener 10 digitos"
            else if (password.length < 6)
                _errormsg.value = "La contraseña debe tener minimo 6 caracteres"
            else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches())
                _errormsg.value = "Ingrese un correo electronico valido"
            else {
                viewModelScope.launch {
                    val result = userRepository.registerUser(email, password)
                    result.let { resourceRemote ->
                        when (resourceRemote) {
                            is ResourceRemote.Succes -> {
                                user = User(result.data, name, email, telefono)
                                createUser(user)

                            }
                            is ResourceRemote.Error -> {
                                var msg = result.message
                                when (result.message) {
                                    "The email address is already in use by another account." -> msg =
                                        "Ya existe una cuenta con este correo."
                                    "The email address is badly formatted." -> msg =
                                        "El email esta mal escrito."
                                    "A network error (such as timeout, interrupted connection or unreachable host) has ocurred." -> msg =
                                        "No tiene conexion a internet"
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

    private fun createUser(user: User) {
        viewModelScope.launch {
            val result = userRepository.createUser(user)
            result.let { resourceRemote ->
                sequenceOf(
                    when (resourceRemote) {
                        is ResourceRemote.Succes -> {
                            _registerSuccess.postValue(result.data)
                            _errormsg.postValue("Registro Exitoso!")
                        }
                        is ResourceRemote.Error -> {
                            var msg = result.message
                            _errormsg.postValue(msg)
                        }
                        else -> {
                            //dont use
                        }
                    }
                )

            }

        }

    }
}