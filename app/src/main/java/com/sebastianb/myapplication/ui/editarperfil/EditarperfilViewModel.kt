package com.sebastianb.myapplication.ui.editarperfil

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sebastianb.myapplication.data.ResourceRemote
import com.sebastianb.myapplication.data.UserRepository
import com.sebastianb.myapplication.model.User
import kotlinx.coroutines.launch


class EditarperfilViewModel : ViewModel() {

    private var auth: FirebaseAuth = Firebase.auth
    private var db = Firebase.firestore
    private val userRepository = UserRepository()
    private val _errormsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errormsg
    private lateinit var user: User
    private val _registerSuccess: MutableLiveData<String?> = MutableLiveData()
    val registerSuccess: LiveData<String?> = _registerSuccess

    fun validateFields(telefono: String, name: String) {


        if (name.isEmpty() || telefono.isEmpty())
            _errormsg.value = "Debe ingresar todos los campos"
        else

            if (telefono.length != 10)
                _errormsg.value = "El telefono debe tener 10 digitos"
            else {
                viewModelScope.launch {
                    val result = updateUser(name, telefono)
                    result.let { resourceRemote ->
                        sequenceOf(
                            when (resourceRemote) {
                                is ResourceRemote.Succes -> {
                                    _registerSuccess.postValue(result.message)
                                    _errormsg.postValue("Actualizacion Exitosa!")
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

    fun updateUser(names: String, telefono: String): ResourceRemote<DocumentReference?> {
        return try {
            val userName = auth.uid?.let { db.collection("users").document(it) }
            userName?.update("name", names, "phone", telefono)
            ResourceRemote.Succes(data = userName)

        } catch (e: FirebaseFirestoreException) {
            Log.e("Register", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException) {
            Log.e("RegisterNetwork", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        }

    }

}
