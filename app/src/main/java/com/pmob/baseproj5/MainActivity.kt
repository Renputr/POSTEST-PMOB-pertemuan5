package com.pmob.baseproj5

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pmob.baseproj5.adapter.UserAdapter
import com.pmob.baseproj5.data.DataUser
import com.pmob.baseproj5.data.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter
    private lateinit var fabAdd: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        fabAdd = findViewById(R.id.fabAdd)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Panggil fungsi untuk load data dari Room
        loadData()

        fabAdd.setOnClickListener {
            startActivity(Intent(this, TambahPostActivity::class.java))
        }
    }

    private fun loadData() {
        lifecycleScope.launch(Dispatchers.IO) {
            val db = UserDatabase.getDatabase(this@MainActivity)
            val users: List<DataUser> = db.userDao().getAllUsers() // langsung List, bukan Flow
            withContext(Dispatchers.Main) {
                adapter = UserAdapter(users)
                recyclerView.adapter = adapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh data setiap kali kembali dari TambahPostActivity
        loadData()
    }
}
