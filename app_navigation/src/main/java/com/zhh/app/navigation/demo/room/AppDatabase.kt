package com.zhh.app.navigation.demo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zhh.app.navigation.demo.model.User

/**
 * Created by zhanghai on 2020/5/19.
 * function：
 */
@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao?

    companion object {
        private var instance: AppDatabase? = null
        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                instance = Room
                        .databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_database")
                        .allowMainThreadQueries()
                        .build()
            }
            return instance
        }
    }
}