package com.example.loginsignupapp

import android.content.Context
import android.content.SharedPreferences
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CredentialsManagerTest {

    private lateinit var credentialsManager: CredentialsManager
    private lateinit var mockContext: Context
    private lateinit var mockSharedPreferences: SharedPreferences
    private lateinit var mockEditor: SharedPreferences.Editor

    @Before
    fun setUp() {
        mockContext = mock()
        mockSharedPreferences = mock()
        mockEditor = mock()

        whenever(mockContext.getSharedPreferences("user_data", Context.MODE_PRIVATE)).thenReturn(mockSharedPreferences)
        whenever(mockSharedPreferences.edit()).thenReturn(mockEditor)
        whenever(mockEditor.putString(Mockito.anyString(), Mockito.anyString())).thenReturn(mockEditor)
        whenever(mockEditor.apply()).then { }

        credentialsManager = CredentialsManager(mockContext)
    }

    @Test
    fun `test register new account`() {
        val email = "test@example.com"
        val password = "password123"

        whenever(mockSharedPreferences.getString(email, null)).thenReturn(null)

        val result = credentialsManager.register(email, password)

        assertEquals("Registration successful", result)
    }

    @Test
    fun `test register existing account`() {
        val email = "test@example.com"
        val password = "password123"

        whenever(mockSharedPreferences.getString(email, null)).thenReturn("password123")

        val result = credentialsManager.register(email, password)

        assertEquals("Email already taken", result)
    }

    @Test
    fun `test validate credentials - valid case`() {
        val email = "test@example.com"
        val password = "password123"

        whenever(mockSharedPreferences.getString(email, null)).thenReturn(password)

        val isValid = credentialsManager.validateCredentials(email, password)

        assertEquals(true, isValid)
    }

    @Test
    fun `test validate credentials - invalid case`() {
        val email = "test@example.com"
        val password = "password123"

        whenever(mockSharedPreferences.getString(email, null)).thenReturn(null)

        val isValid = credentialsManager.validateCredentials(email, password)

        assertEquals(false, isValid)
    }
}
