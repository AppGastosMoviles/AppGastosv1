package com.sebastianb.myapplication.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.toObject
import com.sebastianb.myapplication.data.GastoRepository
import com.sebastianb.myapplication.data.ResourceRemote
import com.sebastianb.myapplication.data.UserRepository
import com.sebastianb.myapplication.model.Gasto
import com.sebastianb.myapplication.model.User
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val userRepository = UserRepository()
    private val gastoRepository=GastoRepository()


    private val _userData: MutableLiveData<String?> = MutableLiveData()
    val userData: LiveData<String?> = _userData



    private val _gastosList: MutableLiveData<String?> = MutableLiveData()
    val gastosList: LiveData<String?> = _gastosList

    fun validateData() {
        viewModelScope.launch {
            val result = userRepository.loadUser()
            result.let { resourceRemote ->
                when (resourceRemote) {
                    is ResourceRemote.Succes -> {
                        result.data?.documents?.forEach { document ->
                            val user = document.toObject<User>()
                                _userData.postValue(user?.name)

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
    fun validateGastoData() {
        viewModelScope.launch {
            val result = gastoRepository.recentGasto()
            result.let { resourceRemote ->
                when (resourceRemote) {
                    is ResourceRemote.Succes -> {
                        var gastoTotal=0.0f
                        result.data?.documents?.forEach{ document ->
                            val gasto=document.toObject<Gasto>()
                            gastoTotal += gasto?.amount!!
                        }
                        _gastosList.postValue("$"+gastoTotal.toString())
                    }

                    is ResourceRemote.Error -> {
                        val msg=result.message

                    }
                    else -> {
                        //dont use
                    }
                }
            }

        }}

}


