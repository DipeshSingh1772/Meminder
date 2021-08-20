package com.example.meminder

import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.meminder.database.Reminder
import com.example.meminder.databinding.FragmentAddReminderBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat


class AddReminderFragment : Fragment(){

    private val viewModel: ReminderViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as ReminderApplication).database
                .reminderDao()
        )
    }

    lateinit var reminder: Reminder

    private var _binding: FragmentAddReminderBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentAddReminderBinding.inflate(inflater, container, false)

        binding.time.setOnClickListener {
            openTimePicker()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveButton.setOnClickListener {
            addNewReminder()
        }
    }

    private fun bind(reminder: Reminder) {
        binding.apply {
            time.setText(reminder.time, TextView.BufferType.SPANNABLE)
            titleText.setText(reminder.title, TextView.BufferType.SPANNABLE)
            Descriptiontext.setText(reminder.description, TextView.BufferType.SPANNABLE)
            // saveButton.setOnClickListener { addNewReminder() }
        }
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.time.text.toString(),
            binding.titleText.text.toString(),
            binding.Descriptiontext.text.toString(),
        )
    }

    private fun addNewReminder() {
        if (isEntryValid()) {
            viewModel.addNewReminder(
                binding.time.text.toString(),
                binding.titleText.text.toString(),
                binding.Descriptiontext.text.toString(),
            )
            val action = AddReminderFragmentDirections.actionAddReminderFragmentToHomeFragment3()
            findNavController().navigate(action)
        }
    }



    // Material Time Picker
    private fun openTimePicker(){
        val isSystem24hour = is24HourFormat(requireContext())
        val clockFormat = if(isSystem24hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H

        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(clockFormat)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Set Time")
            .build()
        picker.show(childFragmentManager,"Tag")

        picker.addOnPositiveButtonClickListener {
            var ampm:String

            if(picker.hour >= 12){
                ampm = "PM"
            }
            else{
                ampm = "AM"
            }

            binding.time.text = "${picker.hour} : ${picker.minute} ${ampm}"
        }
    }



}



