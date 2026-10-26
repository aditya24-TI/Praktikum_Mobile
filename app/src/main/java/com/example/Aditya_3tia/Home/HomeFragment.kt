package com.example.Aditya_3tia.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.Aditya_3tia.DetailActivity
import com.example.Aditya_3tia.Home.pertemuan_5.LimaActivity
import com.example.Aditya_3tia.Home.pertemuan_6.EnamActivity
import com.example.Aditya_3tia.Home.pertemuan_9.NinthActivity
import com.example.Aditya_3tia.LoginActivity
import com.example.Aditya_3tia.R
import com.example.Aditya_3tia.databinding.ActivityMainBinding
import com.example.Aditya_3tia.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Home"
        // 1. Mengambil data yang dikirim dari LoginActivity
        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)
        val username = requireActivity().intent.getStringExtra("EXTRA_USERNAME") ?: "Tidak ada"
        val password = requireActivity().intent.getStringExtra("EXTRA_PASSWORD") ?: "Tidak ada"

        // 2. Menampilkan data ke TextView di UI
        binding.tvResultUsername.text = username
        binding.tvResultPassword.text = password

        // 3. Tombol memunculkan Halaman Detail Project
        binding.btnDetail.setOnClickListener {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            startActivity(intent)
        }

        binding.btnToLima.setOnClickListener {
            val intent = Intent(requireContext(), LimaActivity::class.java)
            startActivity(intent)
        }
        binding.btnToEnam.setOnClickListener {
            val intent = Intent(requireContext(), EnamActivity::class.java)
            startActivity(intent)
        }
        binding.btnToNinth.setOnClickListener {
            val intent = Intent(requireContext(), NinthActivity::class.java)
            startActivity(intent)
        }

        // 4. Tombol Logout dengan konfirmasi AlertDialog
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    dialog.dismiss()
                    startActivity(Intent(requireContext(), LoginActivity::class.java))
                    requireActivity().finish()
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }
}


