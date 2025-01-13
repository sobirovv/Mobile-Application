package com.example.loginsignupapp

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
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
    private lateinit var rememberMeCheckBox: CheckBox
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
        rememberMeCheckBox = findViewById(R.id.rememberMe)

        val sharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE)
        val rememberMe = sharedPreferences.getBoolean("remember_me", false)
        if (rememberMe) {
            val storedEmail = sharedPreferences.getString("email", "")
            val storedPassword = sharedPreferences.getString("password", "")
            emailEditText.setText(storedEmail)
            passwordEditText.setText(storedPassword)
        }

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

            val rememberMeChecked = rememberMeCheckBox.isChecked

            if (valid && credentialsManager.validateCredentials(email, password)) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

                if (rememberMeChecked) {
                    val editor = sharedPreferences.edit()
                    editor.putString("email", email)
                    editor.putString("password", password)
                    editor.putBoolean("remember_me", true)
                    editor.apply()
                } else {
                    val editor = sharedPreferences.edit()
                    editor.remove("email")
                    editor.remove("password")
                    editor.remove("remember_me")
                    editor.apply()
                }

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
