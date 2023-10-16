package com.example.midtermproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val _randomNumber = MutableLiveData<Int>((1..100).random())
    val randomNumber: LiveData<Int> get() = _randomNumber

    private val _attempts = MutableLiveData<Int>(0)
    val attempts: LiveData<Int> get() = _attempts
}
