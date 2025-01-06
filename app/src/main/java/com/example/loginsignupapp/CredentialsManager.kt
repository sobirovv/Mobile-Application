package com.example.loginsignupapp

class CredentialsManager {

    private val accounts: MutableMap<String, String> = mutableMapOf()

    fun register(email: String, password: String): String {
            if (accounts.containsKey(email)) {
                return "Email already taken"
            } else {
                accounts[email] = password
                return "Registration successful"
            }
        }

    fun validateEmail(email: String): Boolean {
        return email.contains("@")
    }

    fun validatePassword(password: String): Boolean {
        return password.length >= 6
    }
    fun validateCredentials(email: String, password: String): Boolean {
        return accounts[email] == password
    }
}