package com.sebastianb.myapplication.ui.agregargasto

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AgregarGastoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Gasto Fragment"
    }

    val text: MutableLiveData<String> = _text
}