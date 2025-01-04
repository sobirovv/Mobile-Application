package com.example.loginsignupapp

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CredentialsManagerTest {

    private val credentialsManager = CredentialsManager()

    @Test
    fun `test empty email string`() {
        val result = credentialsManager.CheckingEmail("")
        assertFalse(result, "Empty email should be invalid")
    }

    @Test
    fun `test wrongly formatted email string`() {
        val result = credentialsManager.CheckingEmail("invalid-email")
        assertFalse(result, "Incorrectly formatted email should be invalid")
    }

    @Test
    fun `test well-formatted email string`() {
        val result = credentialsManager.CheckingEmail("example@test.com")
        assertTrue(result, "Correctly formatted email should be valid")
    }

    @Test
    fun `test empty password string`() {
        val result = credentialsManager.CheckingPassword("")
        assertFalse(result, "Empty password should be invalid")
    }

    @Test
    fun testFilledPassword() {
        val result = credentialsManager.CheckingPassword("securePassword123")
        assertEquals(true, result)
    }
}
