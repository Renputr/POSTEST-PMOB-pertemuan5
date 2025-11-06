package com.example.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pmob.baseproj5.data.UserRepository
import com.pmob.baseproj5.viewmodel.UserViewModel

class UserViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
