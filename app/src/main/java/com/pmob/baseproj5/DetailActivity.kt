package com.pmob.baseproj5

import android.os.Bundle
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.pmob.baseproj5.databinding.ActivityDetailBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var userDao: UserDao
    private var currentUserId1 = 1  // ID postingan pertama
    private var currentUserId2 = 2  // ID postingan kedua

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDao = DatabaseUser.getDatabase(this).userDao()

        // Tombol titik tiga postingan 1
        binding.btnMenu1.setOnClickListener {
            showPopupMenu(1)
        }

        // Tombol titik tiga postingan 2
        binding.btnMenu2.setOnClickListener {
            showPopupMenu(2)
        }
    }

    private fun showPopupMenu(postNumber: Int) {
        val anchor = when (postNumber) {
            1 -> binding.btnMenu1
            2 -> binding.btnMenu2
            else -> return
        }

        val popupMenu = PopupMenu(this, anchor)
        popupMenu.menuInflater.inflate(R.menu.menu_detail, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_update -> {
                    if (postNumber == 1) updateUser(currentUserId1)
                    else updateUser(currentUserId2)
                    true
                }
                R.id.action_delete -> {
                    if (postNumber == 1) deleteUser(currentUserId1)
                    else deleteUser(currentUserId2)
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }

    private fun updateUser(userId: Int) {
        lifecycleScope.launch(Dispatchers.IO) {
            val user = userDao.getUserById(userId)
            if (user != null) {
                val updatedUser = user.copy(
                    username = user.username + " (edited)",
                    caption = user.caption + " [update]"
                )
                userDao.update(updatedUser)

                withContext(Dispatchers.Main) {
                    android.widget.Toast.makeText(
                        this@DetailActivity,
                        "Postingan berhasil diupdate!",
                        android.widget.Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun deleteUser(userId: Int) {
        lifecycleScope.launch(Dispatchers.IO) {
            val user = userDao.getUserById(userId)
            if (user != null) {
                userDao.delete(user)
                withContext(Dispatchers.Main) {
                    android.widget.Toast.makeText(
                        this@DetailActivity,
                        "Postingan berhasil dihapus!",
                        android.widget.Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
