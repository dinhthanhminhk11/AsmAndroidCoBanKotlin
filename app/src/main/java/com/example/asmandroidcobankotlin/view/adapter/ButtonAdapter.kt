package com.example.asmandroidcobankotlin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.asmandroidcobankotlin.databinding.LayoutButtonBinding
import com.example.asmandroidcobankotlin.model.ButtonCustom

class ButtonAdapter(
    private val callback: Callback,
    private val listButton: List<ButtonCustom>,
) : RecyclerView.Adapter<ButtonAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: LayoutButtonBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: LayoutButtonBinding =
            LayoutButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listButton[position]) {
                binding.text.text = this.title
                binding.root.setBackgroundColor(this.backGroupColor)
                binding.root.setOnClickListener {
                    callback.onCLick(this.id)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listButton?.size ?: 0
    }

    interface Callback {
        fun onCLick(id: Int)
    }
}