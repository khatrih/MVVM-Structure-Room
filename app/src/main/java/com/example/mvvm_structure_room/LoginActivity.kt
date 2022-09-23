package com.example.mvvm_structure_room

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_structure_room.application.MvvmApplication
import com.example.mvvm_structure_room.base.BaseActivity
import com.example.mvvm_structure_room.databinding.ActivityLoginBinding
import com.example.mvvm_structure_room.repository.UserRepository
import com.example.mvvm_structure_room.roomsetup.UserDataBase
import com.example.mvvm_structure_room.utils.showToast
import com.example.mvvm_structure_room.viewmodel.MainViewModel
import com.example.mvvm_structure_room.viewmodel.MainViewModelFactory
import com.google.gson.Gson

class LoginActivity : BaseActivity(), View.OnClickListener {
    lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: MainViewModel
    private val USER = "user_details"
    private val USER_ACTIVE = "logged_status"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.login)
        btnClick()
    }

    override fun initViewBinding() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
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

    private fun btnClick() {
        binding.btnLogin.setOnClickListener(this)
        binding.ivUsers.setOnClickListener(this)
        binding.txtRegister.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        if (preferences.getBoolean(USER_ACTIVE, false)) {
            startActivity(Intent(applicationContext, HomeActivity::class.java))
            finish()
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnLogin -> {
                btnLoginClick()
            }

            binding.ivUsers -> {
                startActivity(Intent(this, UsersListActivity::class.java))
            }

            binding.txtRegister -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    private fun btnLoginClick() {
        viewModel.existingUser(
            binding.loginEmail.text.toString(),
            binding.loginPassword.text.toString()
        ).observe(this) {
            if (it == null) {
                showToast(this, getString(R.string.detail_not_found))
            } else {
                showToast(this, getString(R.string.successfully_login))
                startActivity(Intent(this, HomeActivity::class.java))

                val prefEditor: SharedPreferences.Editor = preferences.edit()
                prefEditor.putString(USER, Gson().toJson(it))
                prefEditor.putBoolean(USER_ACTIVE, true)
                prefEditor.apply()
                finish()
            }
        }
    }
}