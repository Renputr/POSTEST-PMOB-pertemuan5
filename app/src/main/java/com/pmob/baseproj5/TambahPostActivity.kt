package com.pmob.baseproj5

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.pmob.baseproj5.databinding.ActivityTambahPostBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TambahPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTambahPostBinding
    private lateinit var dbUser: DatabaseUser
    private lateinit var userDao: UserDao

    private var selectedImageUri: Uri? = null

    // Launcher modern untuk memilih gambar dari galeri
    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                selectedImageUri = result.data?.data
                binding.imgPreview.setImageURI(selectedImageUri)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi database dan DAO
        dbUser = DatabaseUser.getDatabase(applicationContext)
        userDao = dbUser.userDao()

        setupListeners()
    }

    private fun setupListeners() {
        // Tombol untuk memilih gambar
        binding.btnTambahGambar.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = "image/*"
            }
            pickImageLauncher.launch(intent)
        }

        // Tombol Simpan Postingan
        binding.btnSimpan.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val caption = binding.etCaption.text.toString().trim()
            val imageUri = selectedImageUri?.toString()

            if (username.isEmpty() || caption.isEmpty()) {
                Toast.makeText(this, "Semua data harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simpan ke database Room
            lifecycleScope.launch(Dispatchers.IO) {
                val newUser = DataUser(username = username, caption = caption, imageUri = imageUri)
                userDao.insert(newUser)
            }

            Toast.makeText(this, "Post berhasil disimpan!", Toast.LENGTH_SHORT).show()
            finish() // Kembali ke MainActivity
        }
    }
}
