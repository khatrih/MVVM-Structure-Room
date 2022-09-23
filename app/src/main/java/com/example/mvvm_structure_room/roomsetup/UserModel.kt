package com.example.mvvm_structure_room.roomsetup

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val image: String?
)
