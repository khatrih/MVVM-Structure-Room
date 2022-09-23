package com.example.mvvm_structure_room.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_structure_room.retrofit.ApiService
import com.example.mvvm_structure_room.retrofit.CommentModel
import com.example.mvvm_structure_room.roomsetup.UserDao
import com.example.mvvm_structure_room.roomsetup.UserModel
import com.example.mvvm_structure_room.utils.NetworkUtils

class UserRepository(
    private val userDao: UserDao,
    private val apiService: ApiService,
    private val applicationContext: Context
) {

    private val commentLiveData = MutableLiveData<List<CommentModel>>()

    val comment: LiveData<List<CommentModel>>
        get() = commentLiveData

    fun getUsers(): LiveData<List<UserModel>> {
        return userDao.getUsers()
    }

    suspend fun insertingUser(userModel: UserModel) {
        userDao.insertUser(userModel)
    }

    fun existingUser(email: String, password: String): LiveData<UserModel> {
        return userDao.checkExistingUser(email, password)
    }

    suspend fun getComments() {
        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            val result = apiService.getPostComments()
            if (result.body() != null) {
                commentLiveData.postValue(result.body())
            }
        }
    }
}
