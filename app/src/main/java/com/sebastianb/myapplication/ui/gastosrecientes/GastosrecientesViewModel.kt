package com.sebastianb.myapplication.ui.gastosrecientes


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.toObject
import com.sebastianb.myapplication.data.GastoRepository
import com.sebastianb.myapplication.data.ResourceRemote
import com.sebastianb.myapplication.model.Gasto
import kotlinx.coroutines.launch

class GastosrecientesViewModel : ViewModel() {
    private val gastoRepository = GastoRepository()
    private lateinit var foundGastoToDelete: Gasto

   private val _gastoData: MutableLiveData<String?> = MutableLiveData()
    val gastoData: LiveData<String?> = _gastoData

    private val _foundGasto: MutableLiveData<Boolean> = MutableLiveData()
    val foundGasto: LiveData<Boolean> = _foundGasto

    private val _gastoDeleted: MutableLiveData<Boolean?> = MutableLiveData()
    val gastoDeleted: LiveData<Boolean?> = _gastoDeleted

    private var gastoList: ArrayList<Gasto> = ArrayList()

    private val _gastosList: MutableLiveData<ArrayList<Gasto>> = MutableLiveData()
    val gastosList: LiveData<ArrayList<Gasto>> = _gastosList


    fun validateData() {
        viewModelScope.launch {
            val result = gastoRepository.recentGasto()
            result.let { resourceRemote ->
                when (resourceRemote) {
                    is ResourceRemote.Succes -> {
                        var existGasto = false
                        result.data?.documents?.forEach { document ->
                            val gasto = document.toObject<Gasto>()
                            if (gasto?.description?.isNotEmpty() == true) {
                                _gastoData.postValue(
                                    "Nombre" + gasto.description + "\nCategoria" + gasto.category + "\nFecha" + gasto.date + "\nValor" + gasto.amount + "\nEstablecimiento" + gasto.establishment
                                )
                                _foundGasto.postValue(true)
                                foundGastoToDelete = gasto
                            } else {
                                _gastoData.postValue("No tiene gastos agregados")
                                _foundGasto.postValue(false)
                            }
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



    fun deleteGasto() {
        viewModelScope.launch {
            val result = gastoRepository.deleteGasto(foundGastoToDelete)
            result.let { resourceRemote ->
                when (resourceRemote) {
                    is ResourceRemote.Succes -> {
                        _gastoDeleted.postValue(result.data)

                    }
                    is ResourceRemote.Error -> {

                    }
                    else -> {
                        //dont use
                    }
                }


            }
        }
    }

    fun loadGastos() {
        viewModelScope.launch {
            val result = gastoRepository.recentGasto()
            result.let { resourceRemote ->
                when (resourceRemote) {
                    is ResourceRemote.Succes -> {
                            result.data?.documents?.forEach{ document ->
                                val gasto=document.toObject<Gasto>()
                                gasto?.let{gastoList.add(gasto)}
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

