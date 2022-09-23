package com.example.mvvm_structure_room.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_structure_room.repository.UserRepository
import com.example.mvvm_structure_room.retrofit.CommentModel
import com.example.mvvm_structure_room.roomsetup.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private var repository: UserRepository) : ViewModel() {

    fun insertUser(userModel: UserModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertingUser(userModel)
        }
    }

    fun getUserData(): LiveData<List<UserModel>> {
        return repository.getUsers()
    }

    fun existingUser(email: String, password: String): LiveData<UserModel> {
        return repository.existingUser(email, password)
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getComments()
        }
    }

    val comment: LiveData<List<CommentModel>>
        get() = repository.comment
}