package com.example.bootcamp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bootcamp.adapters.CryptoItem

class MainViewModel : ViewModel() {
    val crypto = MutableLiveData<List<CryptoItem>>()
    //CryptoItem
}