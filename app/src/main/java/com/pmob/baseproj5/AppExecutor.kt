package com.pmob.baseproj5.util

import java.util.concurrent.Executors

class AppExecutors {
    private val diskIO = Executors.newSingleThreadExecutor()
    fun diskIO() = diskIO
}
