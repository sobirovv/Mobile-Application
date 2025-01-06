package com.example.loginsignupapp

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CredentialsManagerTest {

    private lateinit var credentialsManager: CredentialsManager

    @Before
    fun setup() {
        credentialsManager = CredentialsManager()
    }

    @Test
    fun testRegisterNewAccount() {
        val result = credentialsManager.register("test@te.st", "password123")
        assertEquals("Registration successful", result)
    }

    @Test
    fun testRegisterWithExistingEmail() {
        credentialsManager.register("test@te.st", "password123")
        val result = credentialsManager.register("test@te.st", "newpassword456")
        assertEquals("Email already taken", result)
    }
}