package com.pmob.baseproj5.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmob.baseproj5.data.DataUser
import com.pmob.baseproj5.data.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    val allUsers: LiveData<List<DataUser>> = repository.allUsers

    fun insert(user: DataUser) = viewModelScope.launch {
        repository.insert(user)
    }

    fun update(user: DataUser) = viewModelScope.launch {
        repository.update(user)
    }

    fun delete(user: DataUser) = viewModelScope.launch {
        repository.delete(user)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun getUserById(id: Int): DataUser? {
        return repository.getUserById(id)
    }
}
