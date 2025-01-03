package com.example.loginsignupapp

class CredentialsManager {
    fun CheckingEmail(email: String): Boolean {
        if (email.isEmpty()) return false
        val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}"
        return email.matches(emailPattern.toRegex())
    }

    fun CheckingPassword(password: String): Boolean {
        return password.isNotEmpty()
    }
}
