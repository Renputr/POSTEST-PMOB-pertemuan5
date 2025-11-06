package com.pmob.baseproj5

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.pmob.baseproj5.data.DataUser
import com.pmob.baseproj5.data.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TambahPostActivity : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var etDeskripsi: EditText
    private lateinit var etTanggal: EditText
    private lateinit var btnSimpan: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_post)

        etNama = findViewById(R.id.etNama)
        etDeskripsi = findViewById(R.id.etDeskripsi)
        etTanggal = findViewById(R.id.etTanggal)
        btnSimpan = findViewById(R.id.btnSimpan)

        btnSimpan.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val deskripsi = etDeskripsi.text.toString().trim()
            val tanggal = etTanggal.text.toString().trim()

            if (nama.isEmpty() || deskripsi.isEmpty() || tanggal.isEmpty()) {
                Toast.makeText(this, "Semua data harus diisi!", Toast.LENGTH_SHORT).show()
            } else {
                val user = DataUser(
                    nama = nama,
                    deskripsi = deskripsi,
                    tanggal = tanggal,
                    gambar = "@drawable/default_image"
                )

                // Simpan data di background thread
                lifecycleScope.launch(Dispatchers.IO) {
                    val db = UserDatabase.getDatabase(this@TambahPostActivity)
                    db.userDao().insert(user)

                    // Balik ke MainActivity di thread utama
                    runOnUiThread {
                        Toast.makeText(
                            this@TambahPostActivity,
                            "Data berhasil disimpan!",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                }
            }
        }
    }
}
