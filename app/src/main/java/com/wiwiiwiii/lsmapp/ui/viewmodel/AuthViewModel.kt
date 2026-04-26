package com.wiwiiwiii.lsmapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wiwiiwiii.lsmapp.data.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val repo = AuthRepository()

    fun register(email: String, password: String) {
        viewModelScope.launch {
            repo.register(email, password)
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            repo.login(email, password)
        }
    }
}