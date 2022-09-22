package com.sebastianb.myapplication.ui.estadisticas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.toObject
import com.sebastianb.myapplication.data.GastoRepository
import com.sebastianb.myapplication.data.ResourceRemote
import com.sebastianb.myapplication.model.Gasto
import kotlinx.coroutines.launch

class EstadisticasViewModel : ViewModel() {
    private val gastoRepository= GastoRepository()
    private val _userAData: MutableLiveData<String?> = MutableLiveData()
    val userAData: LiveData<String?> = _userAData

    private val _userEData: MutableLiveData<String?> = MutableLiveData()
    val userEData: LiveData<String?> = _userEData

    private val _userIData: MutableLiveData<String?> = MutableLiveData()
    val userIData: LiveData<String?> = _userIData

    private val _userPData: MutableLiveData<String?> = MutableLiveData()
    val userPData: LiveData<String?> = _userPData

    private val _userOData: MutableLiveData<String?> = MutableLiveData()
    val userOData: LiveData<String?> = _userOData

    fun Alimentacion() {
        viewModelScope.launch {
            val result = gastoRepository.recentGasto()
            result.let { resourceRemote ->
                when (resourceRemote) {
                    is ResourceRemote.Succes -> {
                        var alitotal=0.0f

                        result.data?.documents?.forEach{ document ->
                            val gasto=document.toObject<Gasto>()
                            if(gasto?.category.toString()=="Alimentación"){
                                alitotal+= gasto?.amount!!
                            }

                        }
                        _userAData.postValue(alitotal.toString())
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
    fun Indumentaria() {
        viewModelScope.launch {
            val result = gastoRepository.recentGasto()
            result.let { resourceRemote ->
                when (resourceRemote) {
                    is ResourceRemote.Succes -> {
                        var indutotal=0.0f
                        result.data?.documents?.forEach{ document ->
                            val gasto=document.toObject<Gasto>()
                            if(gasto?.category.toString()=="Indumentaria"){
                                indutotal+=gasto?.amount!!
                            }

                        }
                        _userIData.postValue(indutotal.toString())
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
    fun Cuidadopersonal() {
        viewModelScope.launch {
            val result = gastoRepository.recentGasto()
            result.let { resourceRemote ->
                when (resourceRemote) {
                    is ResourceRemote.Succes -> {
                        var cuitotal=0.0f

                        result.data?.documents?.forEach{ document ->
                            val gasto=document.toObject<Gasto>()
                            if(gasto?.category.toString()=="Cuidado personal"){
                                cuitotal+=gasto?.amount!!
                            }

                        }
                        _userPData.postValue(cuitotal.toString())
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
    fun Entretenimiento() {
        viewModelScope.launch {
            val result = gastoRepository.recentGasto()
            result.let { resourceRemote ->
                when (resourceRemote) {
                    is ResourceRemote.Succes -> {
                        var entotal=0.0f
                        result.data?.documents?.forEach{ document ->
                            val gasto=document.toObject<Gasto>()

                            if(gasto?.category.toString()=="Entretenimiento"){
                                entotal+=gasto?.amount!!
                            }

                        }
                        _userEData.postValue(entotal.toString())
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
    fun Otros() {
        viewModelScope.launch {
            val result = gastoRepository.recentGasto()
            result.let { resourceRemote ->
                when (resourceRemote) {
                    is ResourceRemote.Succes -> {
                        var ototal=0.0f
                        result.data?.documents?.forEach{ document ->
                            val gasto=document.toObject<Gasto>()

                            if(gasto?.category.toString()=="Otros"){
                                ototal+=gasto?.amount!!
                            }
                        }
                        _userOData.postValue(ototal.toString())
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