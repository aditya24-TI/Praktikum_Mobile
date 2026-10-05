package com.example.Aditya_3tia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.Aditya_3tia.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnLogin.setOnClickListener {
            val inputedUsername = binding.edtUsername.text.toString()
            val inputedPassword = binding.edtPassword.text.toString()

            // Intent untuk berpindah ke MainActivity dan membawa data inputan
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("EXTRA_USERNAME", inputedUsername)
                putExtra("EXTRA_PASSWORD", inputedPassword)
            }
            startActivity(intent)
            finish() // Menutup Activity Login agar tidak bisa diback
        }
    }
}