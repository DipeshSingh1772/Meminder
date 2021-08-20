package com.example.meminder

import androidx.lifecycle.*
import com.example.meminder.database.Reminder
import com.example.meminder.database.ReminderDao
import kotlinx.coroutines.launch

class ReminderViewModel(private val reminderDao: ReminderDao) : ViewModel() {

    val allReminders: LiveData<List<Reminder>> = reminderDao.getAll().asLiveData()


    fun addNewReminder(reminderTime: String, reminderTitle: String, reminderDescription: String) {
        val newItem = getNewReminderEntry(reminderTime,reminderTitle,reminderDescription)
        insertReminder(newItem)
    }

    private fun insertReminder(reminder: Reminder) {
        viewModelScope.launch {
            reminderDao.insert(reminder)
        }
    }

    private fun getNewReminderEntry(reminderTime: String, reminderTitle: String, reminderDescription: String): Reminder {
        return Reminder(
            time = reminderTime,
            title = reminderTitle,
            description = reminderDescription
        )
    }

    fun isEntryValid(reminderTime: String, reminderTitle: String, reminderDescription: String): Boolean {
        if (reminderTime.isBlank() || reminderTitle.isBlank() || reminderDescription.isBlank()) {
            return false
        }
        return true
    }
}

class InventoryViewModelFactory(private val reminderDao: ReminderDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReminderViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReminderViewModel(reminderDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}