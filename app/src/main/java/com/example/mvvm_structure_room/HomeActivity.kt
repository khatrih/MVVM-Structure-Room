package com.example.mvvm_structure_room

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_structure_room.adapters.CommentAdapter
import com.example.mvvm_structure_room.application.MvvmApplication
import com.example.mvvm_structure_room.base.BaseActivity
import com.example.mvvm_structure_room.databinding.ActivityHomeBinding
import com.example.mvvm_structure_room.repository.UserRepository
import com.example.mvvm_structure_room.roomsetup.UserDataBase
import com.example.mvvm_structure_room.roomsetup.UserModel
import com.example.mvvm_structure_room.utils.showToast
import com.example.mvvm_structure_room.viewmodel.MainViewModel
import com.example.mvvm_structure_room.viewmodel.MainViewModelFactory
import com.google.gson.Gson

class HomeActivity : BaseActivity(), View.OnClickListener {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var mainViewModel: MainViewModel
    private val USER = "user_details"

    override fun initViewBinding() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun observerViewModel() {
        val repository = (application as MvvmApplication).repository
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.logoutListener.setOnClickListener(this)

        val json: String? = preferences.getString(USER, "")
        binding.users = Gson().fromJson(json, UserModel::class.java)

        setComments()

    }

    private fun setComments() {
        binding.rvApiUser.layoutManager = LinearLayoutManager(this)
        mainViewModel.comment.observe(this) {
            val adapter = CommentAdapter(it)
            binding.rvApiUser.adapter = adapter
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.logoutListener -> {
                showToast(applicationContext, "logout")
                val editor = preferences.edit()
                editor.clear().apply()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}
