package com.example.loginsignupapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {

    private lateinit var emailContainer: TextInputLayout
    private lateinit var passwordContainer: TextInputLayout
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var signInButton: MaterialButton
    private lateinit var credentialsManager: CredentialsManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        credentialsManager = CredentialsManager(requireContext())

        emailContainer = view.findViewById(R.id.emailContainer)
        passwordContainer = view.findViewById(R.id.passwordContainer)
        emailEditText = view.findViewById(R.id.email)
        passwordEditText = view.findViewById(R.id.password)
        signInButton = view.findViewById(R.id.signInButton)

        val registerNowTextView: TextView = view.findViewById(R.id.tvNewMemberText2)
        registerNowTextView.setOnClickListener {
            (activity as? AuthActivity)?.replaceFragment(RegisterFragment())
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

            if (valid && credentialsManager.validateCredentials(email, password)) {
                Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                // Navigate to HomeActivity or another screen after login
            } else {
                Toast.makeText(requireContext(), "Incorrect email or password", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}
