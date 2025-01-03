package com.example.loginsignupapp
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CredentialsManagerTest {

    private val credentialsManager = CredentialsManager()

    @Test
    fun EmptyEmailString() {
        val result = credentialsManager.CheckingEmail("")
        assertEquals(false, result)
    }

    @Test
    fun WrongFormatEmailString() {
        val result = credentialsManager.CheckingEmail("invalid-email")
        assertEquals(false, result)
    }

    @Test
    fun WellFormatEmailString() {
        val result = credentialsManager.CheckingEmail("example@test.com")
        assertEquals(true, result)
    }

    @Test
    fun EmptyPasswordString() {
        val result = credentialsManager.CheckingPassword("")
        assertEquals(false, result)
    }

    @Test
    fun FilledPasswordString() {
        val result = credentialsManager.CheckingPassword("securePassword123")
        assertEquals(true, result)
    }
}