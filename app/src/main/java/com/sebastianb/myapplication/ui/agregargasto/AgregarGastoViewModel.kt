package com.sebastianb.myapplication.ui.agregargasto

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastianb.myapplication.data.GastoRepository
import com.sebastianb.myapplication.data.ResourceRemote
import com.sebastianb.myapplication.model.Gasto
import kotlinx.coroutines.launch

class AgregarGastoViewModel : ViewModel() {


    private val _errormsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errormsg
    private val _createGastoSuccess:MutableLiveData<String?> = MutableLiveData()
    val createGastoSuccess:LiveData<String?> = _createGastoSuccess
    private val gastoRepository= GastoRepository()
    fun validateFields(
        descripcion: String,
        valor: String,
        establecimiento: String,
        categoria: String,
        fecha:String
    ){
        if(descripcion.isEmpty()||valor.isEmpty()||establecimiento.isEmpty()||fecha.isEmpty())
            _errormsg.value = "Debe ingresar todos los campos"
        else
            if (categoria == "Alimentación" || categoria == "Indumentaria" || categoria == "Entretenimiento" || categoria == "Cuidado personal" || categoria == "Otros")
                viewModelScope.launch {
                    val gasto=Gasto(date=fecha, category = categoria, description = descripcion, amount = valor.toFloat(), establishment = establecimiento)
                    val result=gastoRepository.createGasto(gasto)
                    result.let {resourceRemote ->
                        when(resourceRemote){
                            is ResourceRemote.Succes ->{
                                _createGastoSuccess.postValue(result.data)
                                _errormsg.postValue("Gasto añadido")
                            }
                            is ResourceRemote.Error ->{
                                val msg=result.message
                                _errormsg.postValue(msg)
                            }
                            else ->{
                                //dont use
                            }



                        }
                    }
                }

            else
                _errormsg.value = "La categoria no puede estar vacia"


    }
}
