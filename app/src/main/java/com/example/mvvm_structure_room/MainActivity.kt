package com.example.mvvm_structure_room

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_structure_room.application.MvvmApplication
import com.example.mvvm_structure_room.base.BaseActivity
import com.example.mvvm_structure_room.databinding.ActivityMainBinding
import com.example.mvvm_structure_room.roomsetup.UserModel
import com.example.mvvm_structure_room.viewmodel.MainViewModel
import com.example.mvvm_structure_room.viewmodel.MainViewModelFactory

class MainActivity : BaseActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var profileImage: Uri? = null

    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()) {
        profileImage = it
        binding.ivProfileImage.setImageURI(it)
    }

    override fun initViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun observerViewModel() {
        val repository = (application as MvvmApplication).repository
        viewModel =
            ViewModelProvider(
                this,
                MainViewModelFactory(repository)
            ).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.registration)

        binding.btnRegister.setOnClickListener(this)
        binding.addProfile.setOnClickListener(this)
        binding.ivUsers.setOnClickListener(this)
        binding.directLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnRegister -> {
                btnRegister()
            }
            binding.addProfile -> {
                contract.launch("image/*")
            }
            binding.ivUsers -> {
                startActivity(Intent(this.applicationContext, UsersListActivity::class.java))
            }
            binding.directLogin -> {
                finish()
            }
        }
    }

    private fun btnRegister() {
        val userModel = UserModel(
            null, binding.firstName.text.toString(),
            binding.lastName.text.toString(),
            binding.email.text.toString(),
            binding.password.text.toString(),
            profileImage.toString()
        )
        viewModel.insertUser(userModel)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}