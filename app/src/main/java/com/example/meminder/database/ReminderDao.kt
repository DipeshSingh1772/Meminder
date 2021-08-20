package com.example.meminder.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(reminder: Reminder)

    @Update
    suspend fun update(reminder: Reminder)

    @Delete
    suspend fun delete(reminder: Reminder)

    @Query("SELECT * from reminderrecord ORDER BY Time ASC")
    fun getItems(): Flow<List<Reminder>>

    @Query("SELECT * from ReminderRecord WHERE id = :id")
    fun getItem(id: Int): Flow<Reminder>
}