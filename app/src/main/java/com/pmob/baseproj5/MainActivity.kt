package com.pmob.baseproj5

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmob.baseproj5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dbUser: DatabaseUser
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi database & DAO
        dbUser = DatabaseUser.getDatabase(applicationContext)
        userDao = dbUser.userDao()

        setupRecyclerView()
        observeData()
        setupListeners()
    }

    private fun setupRecyclerView() {
        binding.rvRoomDb.layoutManager = LinearLayoutManager(this)
    }

    private fun observeData() {
        // Observasi LiveData dari database
        userDao.getAllUser().observe(this, Observer { list ->
            binding.rvRoomDb.adapter = UserAdapter(list)
        })
    }

    private fun setupListeners() {
        // FAB tambah post baru
        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, TambahPostActivity::class.java)
            startActivity(intent)
        }
    }
}
