package com.example.mvvm_structure_room.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_structure_room.databinding.ItemUsersBinding
import com.example.mvvm_structure_room.roomsetup.UserModel

class UserAdapter(private val mList: List<UserModel>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ItemUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(userModel: UserModel) {
            binding.users = userModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userModel = mList[position]
        holder.bindData(userModel)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}