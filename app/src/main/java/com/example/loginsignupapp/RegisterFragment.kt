package com.example.loginsignupapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterFragment : Fragment() {

    private lateinit var emailContainer: TextInputLayout
    private lateinit var passwordContainer: TextInputLayout
    private lateinit var confirmPasswordContainer: TextInputLayout
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var confirmPasswordEditText: TextInputEditText
    private lateinit var registerButton: MaterialButton
    private lateinit var credentialsManager: CredentialsManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        credentialsManager = CredentialsManager(requireContext())

        emailContainer = view.findViewById(R.id.emailContainer)
        passwordContainer = view.findViewById(R.id.passwordContainer)
        confirmPasswordContainer = view.findViewById(R.id.confirmPasswordContainer)
        emailEditText = view.findViewById(R.id.email)
        passwordEditText = view.findViewById(R.id.password)
        confirmPasswordEditText = view.findViewById(R.id.confirmPassword)
        registerButton = view.findViewById(R.id.registerButton)

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

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

            if (password != confirmPassword) {
                confirmPasswordContainer.error = "Passwords do not match"
                valid = false
            } else {
                confirmPasswordContainer.error = null
            }

            if (valid) {
                credentialsManager.register (email, password)
                Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show()
                (activity as? AuthActivity)?.replaceFragment(LoginFragment())
            } else {
                Toast.makeText(requireContext(), "Please fix the errors", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegisterFragment()
    }
}
