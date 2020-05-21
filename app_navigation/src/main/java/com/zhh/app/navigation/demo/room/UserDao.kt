package com.zhh.app.navigation.demo.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.zhh.app.navigation.demo.model.User

/**
 * Created by zhanghai on 2020/5/19.
 * function：
 */
@Dao
interface UserDao {
    @Query("select * from user")
    fun getUsers(): LiveData<List<User>>

    @Insert
    fun insertUser(user: User?)
}