package com.example.meminder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meminder.database.Reminder

class ReminderListAdapter(private val onItemClicked: (Reminder) -> Unit)
    : RecyclerView.Adapter<ReminderListAdapter.ReminderDataVH>() {

    private val allData = ArrayList<Reminder>()

    inner class ReminderDataVH(itemView: View): RecyclerView.ViewHolder(itemView){
        val reminderTitle: TextView = itemView.findViewById(R.id.adapter_title)
        val reminderTime:TextView = itemView.findViewById(R.id.adapter_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderDataVH {
        val viewHolder = ReminderDataVH(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_list,parent,false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: ReminderDataVH, position: Int) {

        val positionOnScreen = allData[position]
        holder.reminderTitle.text = positionOnScreen.title
        holder.reminderTime.text = positionOnScreen.time
        holder.itemView.setOnClickListener {
            onItemClicked(positionOnScreen)
        }
    }

    override fun getItemCount(): Int {
        return allData.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAllData(itemList:List<Reminder>){

        allData.clear()
        allData.addAll(itemList)

        notifyDataSetChanged()
    }
}