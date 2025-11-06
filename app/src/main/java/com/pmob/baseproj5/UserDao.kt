package com.pmob.baseproj5.data

import androidx.room.*

@Dao
interface UserDao {

    // Ambil semua data user secara langsung (bukan Flow)
    @Query("SELECT * FROM user_table ORDER BY id DESC")
    fun getAllUsers(): List<DataUser>

    // Insert data (sinkron)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: DataUser): Long

    // Update data
    @Update
    fun update(user: DataUser)

    // Hapus satu data
    @Delete
    fun delete(user: DataUser)

    // Hapus semua data
    @Query("DELETE FROM user_table")
    fun deleteAll()

    // Ambil user berdasarkan ID
    @Query("SELECT * FROM user_table WHERE id = :userId LIMIT 1")
    fun getUserById(userId: Int): DataUser?
}
