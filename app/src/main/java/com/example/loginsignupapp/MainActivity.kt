package com.example.loginsignupapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var emailContainer: TextInputLayout
    private lateinit var passwordContainer: TextInputLayout
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var signInButton: MaterialButton
    private lateinit var credentialsManager: CredentialsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        credentialsManager = CredentialsManager(this)

        val registerNowTextView: TextView = findViewById(R.id.tvNewMemberText2)
        registerNowTextView.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }

        emailContainer = findViewById(R.id.emailContainer)
        passwordContainer = findViewById(R.id.passwordContainer)
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        signInButton = findViewById(R.id.signInButton)

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            var valid = true

            if (!credentialsManager.validateEmail(email)) {
                emailContainer.error = "Invalid email address"
                valid = false
            } else {
                emailContainer.error = null
            }

            if (!credentialsManager.validatePassword(password)) {
                passwordContainer.error = "Password cannot be empty"
                valid = false
            } else {
                passwordContainer.error = null
            }

            if (valid && credentialsManager.validateCredentials(email, password)) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

