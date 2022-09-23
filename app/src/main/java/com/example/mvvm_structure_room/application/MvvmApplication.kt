package com.example.mvvm_structure_room.application

import android.app.Application
import com.example.mvvm_structure_room.repository.UserRepository
import com.example.mvvm_structure_room.retrofit.ApiService
import com.example.mvvm_structure_room.retrofit.RetrofitHelper
import com.example.mvvm_structure_room.roomsetup.UserDataBase

class MvvmApplication : Application() {
    lateinit var repository: UserRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val dao = UserDataBase.getDataBase(applicationContext).userDao()
        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        repository = UserRepository(dao, apiService, applicationContext)
    }
}