package com.example.mvvm_structure_room.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_structure_room.databinding.ItemCoomentListBinding
import com.example.mvvm_structure_room.retrofit.CommentModel

class CommentAdapter(private val commentModel: List<CommentModel>) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ItemCoomentListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(commentModel: CommentModel) {
            binding.comment = commentModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemCoomentListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val commentModel = commentModel[position]
        holder.bindView(commentModel)
    }

    override fun getItemCount(): Int {
        return commentModel.size
    }
}