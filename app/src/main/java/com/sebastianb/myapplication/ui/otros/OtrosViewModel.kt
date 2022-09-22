package com.sebastianb.myapplication.ui.otros

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.toObject
import com.sebastianb.myapplication.data.GastoRepository
import com.sebastianb.myapplication.data.ResourceRemote
import com.sebastianb.myapplication.model.Gasto
import kotlinx.coroutines.launch

class OtrosViewModel : ViewModel() {
    private val gastoRepository = GastoRepository()
    private var gastoList: ArrayList<Gasto> = ArrayList()

    private val _gastosList: MutableLiveData<ArrayList<Gasto>> = MutableLiveData()
    val gastosList: LiveData<ArrayList<Gasto>> = _gastosList

    fun loadGastos() {
        viewModelScope.launch {
            val result = gastoRepository.recentGasto()
            result.let { resourceRemote ->
                when (resourceRemote) {
                    is ResourceRemote.Succes -> {
                        result.data?.documents?.forEach{ document ->
                            val gasto=document.toObject<Gasto>()
                            if(gasto?.category.toString()=="Otros"){gasto?.let{gastoList.add(gasto)}}
                        }
                        _gastosList.postValue(gastoList)
                    }

                    is ResourceRemote.Error -> {
                        val msg=result.message

                    }
                    else -> {
                        //dont use
                    }
                }
            }

        }

    }
}