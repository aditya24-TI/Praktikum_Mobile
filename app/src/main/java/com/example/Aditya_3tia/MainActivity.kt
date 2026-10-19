package com.example.Aditya_3tia

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.Aditya_3tia.databinding.ActivityMainBinding
import com.example.Aditya_3tia.Home.pertemuan_5.LimaActivity
import com.example.Aditya_3tia.Home.pertemuan_6.EnamActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Menggunakan ViewBinding untuk MainActivity
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur window insets menggunakan binding.root / binding.main tanpa findViewById
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Mengambil data yang dikirim dari LoginActivity
        val username = intent.getStringExtra("EXTRA_USERNAME") ?: "Tidak ada"
        val password = intent.getStringExtra("EXTRA_PASSWORD") ?: "Tidak ada"

        // 2. Menampilkan data ke TextView di UI
        binding.tvResultUsername.text = username
        binding.tvResultPassword.text = password

        // 3. Tombol memunculkan Halaman Detail Project
        binding.btnDetail.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        // 4. Tombol Logout dengan konfirmasi AlertDialog
        binding.btnLogout.setOnClickListener {
            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    dialog.dismiss()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }

            binding.btnToLima.setOnClickListener {
                val intent = Intent(this@MainActivity, LimaActivity::class.java)
                startActivity(intent)
            }
            binding.btnToEnam.setOnClickListener {
                val intent = Intent(this@MainActivity, EnamActivity::class.java)
                startActivity(intent)
            }
        }
    }
