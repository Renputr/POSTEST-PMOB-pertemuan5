package com.pmob.baseproj5

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_user")
data class DataUser(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var username: String,
    var caption: String,
    var imageUri: String? = null
)
