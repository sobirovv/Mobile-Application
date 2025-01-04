package com.example.loginsignupapp

class CredentialsManager {

    fun CheckingEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun CheckingPassword(password: String): Boolean {
        return password.isNotEmpty()
    }
}

