package com.example.app

import android.app.Application
import com.pmob.baseproj5.data.UserRepository

class UserApp : Application() {
    val database by lazy { DatabaseUser.getDatabase(this) }
    val repository by lazy { UserRepository(database.userDao()) }
}
