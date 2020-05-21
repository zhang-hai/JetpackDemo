package com.zhh.app.navigation.demo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.zhh.app.navigation.demo.model.User
import com.zhh.app.navigation.demo.room.UserRepository

/**
 * Created by zhanghai on 2020/5/18.
 * function：
 */
class NavigationViewModel(application: Application) : AndroidViewModel(application) {
    private var userLiveData: LiveData<List<User>>

    private var userRepository:UserRepository

    init {
        userRepository = UserRepository(application)
        userLiveData = userRepository.userLive
    }

    fun insertUser(user : User){
        userRepository.insertUser(user)
    }

    fun getUserLiveData(): LiveData<List<User>> {
        return userLiveData
    }


}