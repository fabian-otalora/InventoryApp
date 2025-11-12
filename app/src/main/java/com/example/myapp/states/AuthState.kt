package com.example.myapp.states

data class AuthState(
    val email: String = "",
    val password: String = "",
    val username: String = "",
    val isLoggedIn: Boolean = false,
    val error: String? = null
)