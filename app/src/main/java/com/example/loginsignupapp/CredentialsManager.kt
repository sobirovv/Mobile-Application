package com.example.loginsignupapp

class CredentialsManager {

    fun validateEmail(email: String): Boolean {
        if (email.isEmpty()) {
            return false
        }

        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        return email.matches(emailRegex)
    }

    fun validatePassword(password: String): Boolean {
        return password.isNotEmpty()
    }
}
