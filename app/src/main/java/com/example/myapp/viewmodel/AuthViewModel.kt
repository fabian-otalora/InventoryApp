package com.example.myapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.models.User
import com.example.myapp.room.UserDao
import com.example.myapp.states.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val userDao: UserDao) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state

    fun onEmailChange(value: String) {
        _state.value = _state.value.copy(email = value)
    }

    fun onPasswordChange(value: String) {
        _state.value = _state.value.copy(password = value)
    }

    fun onUsernameChange(value: String) {
        _state.value = _state.value.copy(username = value)
    }

    fun register() {
        viewModelScope.launch {
            val email = _state.value.email.trim()
            val password = _state.value.password.trim()
            val username = _state.value.username.trim()

            // 🔹 Validación
            if (email.isEmpty() || password.isEmpty() || username.isEmpty()) {
                _state.value = _state.value.copy(error = "Por favor completa todos los campos")
                return@launch
            }

            val existing = userDao.getUserByEmail(email)
            if (existing != null) {
                _state.value = _state.value.copy(error = "El correo ya está registrado")
                return@launch
            }

            userDao.registerUser(User(username = username, email = email, password = password))
            _state.value = _state.value.copy(isLoggedIn = true, error = null)
        }
    }

    fun login() {
        viewModelScope.launch {
            val email = _state.value.email.trim()
            val password = _state.value.password.trim()

            // 🔹 Validación de campos vacíos
            if (email.isEmpty() || password.isEmpty()) {
                _state.value = _state.value.copy(error = "Por favor completa todos los campos")
                return@launch
            }

            val user = userDao.loginUser(email, password)
            if (user != null) {
                _state.value = _state.value.copy(isLoggedIn = true, error = null)
            } else {
                _state.value = _state.value.copy(error = "Credenciales incorrectas")
            }
        }
    }

    fun logout() {
        _state.value = AuthState(isLoggedIn = false)
    }
}
