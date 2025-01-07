package com.example.loginsignupapp

import android.content.Context

class CredentialsManager(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    fun register(email: String, password: String): String {
        val existingAccount = sharedPreferences.getString(email, null)
        return if (existingAccount != null) {
            "Email already taken"
        } else {
            with(sharedPreferences.edit()) {
                putString(email, password)
                apply()
            }
            "Registration successful"
        }
    }

    fun validateEmail(email: String): Boolean {
        return email.contains("@")
    }

    fun validatePassword(password: String): Boolean {
        return password.length >= 6
    }

    fun validateCredentials(email: String, password: String): Boolean {
        val storedPassword = sharedPreferences.getString(email, null)
        return storedPassword == password
    }
}