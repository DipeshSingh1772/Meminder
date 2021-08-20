package com.example.meminder

import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.meminder.databinding.FragmentAddReminderBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat


class AddReminderFragment : Fragment(){

    private var _binding: FragmentAddReminderBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentAddReminderBinding.inflate(inflater, container, false)

        binding.time.setOnClickListener {
            openTimePicker()
        }


        return binding.root
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



