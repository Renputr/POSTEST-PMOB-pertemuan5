package com.pmob.baseproj5.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val allUsers: LiveData<List<DataUser>> = userDao.getAllUsers() as LiveData<List<DataUser>>

    @WorkerThread
    fun insert(user: DataUser) {
        userDao.insert(user)
    }

    @WorkerThread
    fun update(user: DataUser) {
        userDao.update(user)
    }

    @WorkerThread
    fun delete(user: DataUser) {
        userDao.delete(user)
    }

    @WorkerThread
    fun deleteAll() {
        userDao.deleteAll()
    }

    fun getUserById(id: Int): DataUser? {
        return userDao.getUserById(id)
    }
}
