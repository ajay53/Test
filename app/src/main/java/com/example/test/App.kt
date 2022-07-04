package com.example.test

import android.app.Application
import com.example.test.room.DatabaseHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App:Application() {
    // No need to cancel this scope as it'll be torn down with the process
    private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { DatabaseHandler.getDatabase(this, applicationScope) }
    val mainRepository by lazy { MainRepository(database.todosDao()) }
}