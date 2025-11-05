package com.pmob.baseproj5

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [DataUser::class],
    version = 1,
    exportSchema = false // Tambahkan ini biar Room tidak bingung generate schema
)
abstract class DatabaseUser : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseUser? = null

        fun getDatabase(context: Context): DatabaseUser {
            return INSTANCE ?: synchronized(this) {
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseUser::class.java,
                    "user_database"
                )
                    .fallbackToDestructiveMigration() // Tambahan penting: cegah error versi DB
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
