package com.geco.bpadapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.geco.bpadapp.databinding.ActivityLoginBinding
import com.geco.bpadapp.ui.dashboard.DashboardActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            validateInput()
        }
    }
    private fun validateInput() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        binding.textInputLayoutEmail.error = null
        binding.textInputLayoutPassword.error = null

        var isValid = true

        if (email.isEmpty()) {
            binding.textInputLayoutEmail.error = "Email tidak boleh kosong"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.textInputLayoutEmail.error = "Format email tidak valid"
            isValid = false
        }

        if (password.isEmpty()) {
            binding.textInputLayoutPassword.error = "Password tidak boleh kosong"
            isValid = false
        }

        if (isValid) {
            performLogin(email, password)
        }
    }

    private fun performLogin(email: String, password: String) {
        loginViewModel.login(email, password) { success, error ->
            if (success) {
                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                loginViewModel.authToken.observe(this) { token ->
                    println("TOKEN : $token")
                    if (token != null) {
                        loginViewModel.fetchUsers(token)
                    }

                    loginViewModel.userFetchStatus.observe(this){
                        val intent = Intent(this, DashboardActivity::class.java)
                        intent.putExtra("auth_token", token)
                        startActivity(intent)
                        finish()
                    }
                }
            } else {
                Toast.makeText(this, "Login Gagal: $error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}