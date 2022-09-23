package com.example.mvvm_structure_room.base

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    abstract fun initViewBinding()
    abstract fun observerViewModel()

    lateinit var preferences: SharedPreferences
    private val PREFERENCE_NAME = "logged_record"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        observerViewModel()

        preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)

    }
}