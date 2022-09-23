package com.example.mvvm_structure_room.roomsetup

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getUsers(): LiveData<List<UserModel>>

    @Insert
    suspend fun insertUser(userModel: UserModel)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    fun checkExistingUser(email: String, password: String): LiveData<UserModel>
}