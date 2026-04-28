package com.wiwiiwiii.lsmapp.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.wiwiiwiii.lsmapp.data.AuthRepository
import kotlinx.coroutines.launch
import android.util.Patterns
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.wiwiiwiii.lsmapp.data.SessionManager

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = AuthRepository()
    private val session = SessionManager(application)

    private val _state = mutableStateOf<AuthState>(AuthState.Idle)
    val state: State<AuthState> = _state

    fun login(email: String, password: String, context: Context) {

        if (email.isBlank() || password.isBlank()) {
            _state.value = AuthState.Error("Completa todos los campos")
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _state.value = AuthState.Error("Correo inválido")
            return
        }

        viewModelScope.launch {
            _state.value = AuthState.Loading

            try {
                val response = repo.login(email, password)

                // VALIDAR TOKEN CORRECTAMENTE
                val token = response.access_token
                    ?: throw Exception("No se recibió token")

                val session = SessionManager(context)
                session.saveToken(token)

                println("TOKEN GUARDADO: $token")

                _state.value = AuthState.Success

            } catch (e: Exception) {
                e.printStackTrace()
                _state.value = AuthState.Error(e.message ?: "Login incorrecto")
            }
        }
    }

    fun register(email: String, password: String) {

        if (email.isBlank() || password.isBlank()) {
            _state.value = AuthState.Error("Faltan datos")
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _state.value = AuthState.Error("Correo inválido")
            return
        }

        if (password.length < 6) {
            _state.value = AuthState.Error("Contraseña muy corta")
            return
        }

        viewModelScope.launch {
            _state.value = AuthState.Loading

            try {
                val result = repo.register(email, password)

                _state.value = AuthState.Success

            } catch (e: Exception) {

                _state.value = AuthState.Error(
                    e.message ?: "Error al registrar"
                )
            }
        }
    }

    fun resetState() {
        _state.value = AuthState.Idle
    }
}