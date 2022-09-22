package com.sebastianb.myapplication.ui.perfil

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.toObject
import com.sebastianb.myapplication.data.ResourceRemote
import com.sebastianb.myapplication.data.UserRepository
import com.sebastianb.myapplication.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PerfilViewModel : ViewModel() {
    private val userRepository = UserRepository()


    private val _userNameData: MutableLiveData<String?> = MutableLiveData()
    val userNameData: LiveData<String?> = _userNameData

    private val _userEmailData: MutableLiveData<String?> = MutableLiveData()
    val userEmailData: LiveData<String?> = _userEmailData

    private val _userPhoneData: MutableLiveData<String?> = MutableLiveData()
    val userPhoneData: LiveData<String?> = _userPhoneData

    fun validateNameData() {
        viewModelScope.launch {
            val result = userRepository.loadUser()
            result.let { resourceRemote ->
                when (resourceRemote) {
                    is ResourceRemote.Succes -> {
                        result.data?.documents?.forEach { document ->
                            val user = document.toObject<User>()
                            _userNameData.postValue("Nombre: "+user?.name)

                        }


                    }
                    is ResourceRemote.Error -> {
                        val msg = result.message

                    }
                    else -> {
                        //dont use
                    }
                }

            }
            Log.d("Resultado", result.toString())
        }
    }
    fun validateEmailData() {
        viewModelScope.launch {
            val result = userRepository.loadUser()
            result.let { resourceRemote ->
                when (resourceRemote) {
                    is ResourceRemote.Succes -> {
                        result.data?.documents?.forEach { document ->
                            val user = document.toObject<User>()
                            _userEmailData.postValue("Correo electronico: "+user?.email)

                        }


                    }
                    is ResourceRemote.Error -> {
                        val msg = result.message

                    }
                    else -> {
                        //dont use
                    }
                }

            }
            Log.d("Resultado", result.toString())
        }
    }
    fun validatePhoneData() {
        viewModelScope.launch {
            val result = userRepository.loadUser()
            result.let { resourceRemote ->
                when (resourceRemote) {
                    is ResourceRemote.Succes -> {
                        result.data?.documents?.forEach { document ->
                            val user = document.toObject<User>()
                            _userPhoneData.postValue("Telefono: "+user?.phone)

                        }


                    }
                    is ResourceRemote.Error -> {
                        val msg = result.message

                    }
                    else -> {
                        //dont use
                    }
                }

            }
            Log.d("Resultado", result.toString())
        }
    }
    fun logOut(){
        GlobalScope.launch(Dispatchers.IO) {
            val result=userRepository.logOutUser()
                }
            }


}