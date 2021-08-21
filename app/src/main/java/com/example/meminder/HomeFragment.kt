package com.example.meminder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meminder.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val viewModel: ReminderViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as ReminderApplication).database
                .reminderDao()
        )
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.AddButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment3_to_addReminderFragment)
        }

        binding.bottomAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search -> {
                    // Handle search icon press
                    true
                }
                R.id.more -> {
                    // Handle more item (inside overflow menu) press
                    true
                }
                else -> false
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Creating Recycler View instance and bind it in MainActivity xml file recycler view
        binding.reminderRecyclerView.layoutManager = LinearLayoutManager(this.context)
        val adapter = ReminderListAdapter {
            val action = HomeFragmentDirections.actionHomeFragment3ToAddReminderFragment()
            findNavController().navigate(action)
        }

        binding.reminderRecyclerView.adapter = adapter

        //observer when data change it send new list of data to adapter
        viewModel.allReminders.observe(this.viewLifecycleOwner,Observer{ List ->
            List?.let {
                adapter.updateAllData(it)
            }
        })
    }
}