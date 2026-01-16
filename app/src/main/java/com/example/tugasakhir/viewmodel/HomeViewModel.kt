package com.example.tugasakhir.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {

    private val _isGuru = MutableStateFlow(false)
    val isGuru: StateFlow<Boolean> = _isGuru

    fun setRole(role: String) {
        _isGuru.value = role.equals("guru", ignoreCase = true)
    }
}
