package com.example.loginsignupapp

import org.junit.Test
import org.junit.Assert.*

class CredentialsManagerTest {

    private val credentialsManager = CredentialsManager()

    @Test
    fun testEmptyEmail() {
        assertFalse(credentialsManager.validateEmail(""))
    }

    @Test
    fun testInvalidEmailFormat() {
        assertFalse(credentialsManager.validateEmail("test@com"))
    }

    @Test
    fun testValidEmail() {
        assertTrue(credentialsManager.validateEmail("test@example.com"))
    }

    @Test
    fun testEmptyPassword() {
        assertFalse(credentialsManager.validatePassword(""))
    }

    @Test
    fun testFilledPassword() {
        assertTrue(credentialsManager.validatePassword("password123"))
    }
}
