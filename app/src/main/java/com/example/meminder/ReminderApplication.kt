package com.example.meminder

import android.app.Application
import com.example.meminder.database.ReminderDatabase

class ReminderApplication: Application() {
    // Using by lazy so the database is only created when needed
    // rather than when the application starts
    val database: ReminderDatabase by lazy { ReminderDatabase.getDatabase(this) }
}