package com.pmob.baseproj5

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    // Tambah user baru
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dataUser: DataUser): Long

    // Update data user
    @Update
    suspend fun update(dataUser: DataUser)

    // Hapus data user
    @Delete
    suspend fun delete(dataUser: DataUser)

    // Ambil semua user
    @Query("SELECT * FROM tb_user ORDER BY id ASC")
    fun getAllUser(): LiveData<List<DataUser>>

    // Ambil satu user berdasarkan ID
    @Query("SELECT * FROM tb_user WHERE id = :userId LIMIT 1")
    suspend fun getUserById(userId: Int): DataUser?
}
