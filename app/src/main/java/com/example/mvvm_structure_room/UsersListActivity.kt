package com.example.mvvm_structure_room

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_structure_room.adapters.UserAdapter
import com.example.mvvm_structure_room.application.MvvmApplication
import com.example.mvvm_structure_room.base.BaseActivity
import com.example.mvvm_structure_room.databinding.ActivityUsersListBinding
import com.example.mvvm_structure_room.repository.UserRepository
import com.example.mvvm_structure_room.roomsetup.UserDataBase
import com.example.mvvm_structure_room.utils.gone
import com.example.mvvm_structure_room.utils.visible
import com.example.mvvm_structure_room.viewmodel.MainViewModel
import com.example.mvvm_structure_room.viewmodel.MainViewModelFactory

class UsersListActivity : BaseActivity() {
    private lateinit var binding: ActivityUsersListBinding
    private lateinit var viewModel: MainViewModel

    override fun initViewBinding() {
        binding = ActivityUsersListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun observerViewModel() {
        val repository = (application as MvvmApplication).repository
        viewModel =
            ViewModelProvider(
                this,
                MainViewModelFactory(repository)
            )[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRecyclerViewList()
    }

    private fun setRecyclerViewList() {
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        viewModel.getUserData().observe(this) {
            if (!it.isNullOrEmpty()) {
                binding.emptyData.gone()
                binding.rvUser.visible()
                val adapter = UserAdapter(it)
                binding.rvUser.adapter = adapter
            } else {
                binding.emptyData.visible()
                binding.rvUser.gone()
            }
        }
    }

}