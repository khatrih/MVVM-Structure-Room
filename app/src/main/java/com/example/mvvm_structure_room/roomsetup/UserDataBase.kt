package com.example.mvvm_structure_room.roomsetup

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserModel::class], version = 1)
abstract class UserDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: UserDataBase? = null
        fun getDataBase(context: Context): UserDataBase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserDataBase::class.java, "TestDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}