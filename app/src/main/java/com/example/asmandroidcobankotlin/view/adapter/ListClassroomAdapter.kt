package com.example.asmandroidcobankotlin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.asmandroidcobankotlin.databinding.ItemClassroomBinding
import com.example.asmandroidcobankotlin.model.Classroom
import com.example.asmandroidcobankotlin.view.adapter.ListClassroomAdapter.ViewHolder

class ListClassroomAdapter(private val listClassroom: List<Classroom>) :
    RecyclerView.Adapter<ViewHolder>() {

    inner class ViewHolder(val binding: ItemClassroomBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemClassroomBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listClassroom[position]) {
                var count = 0
                binding.nameClassroom.text = this.nameClass
                binding.codeClass.text = this.id
                binding.number.text = position.toString()
            }
        }
    }

    override fun getItemCount(): Int {
        return listClassroom?.size ?: 0
    }
}