package com.example.ro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.ro.databinding.ItemDataBinding

class Adapter : ListAdapter<ResultsItem, Adapter.ViewHolder>(ResultsItemDiffCallback()) {
    class ViewHolder(val binding: ItemDataBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener{
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = getItem(position)
        holder.binding.result = result
    }

    private class ResultsItemDiffCallback : DiffUtil.ItemCallback<ResultsItem>() {
        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem == newItem
        }
    }
}