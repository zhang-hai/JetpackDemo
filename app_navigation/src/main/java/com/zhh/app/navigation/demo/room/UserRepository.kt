package com.zhh.app.navigation.demo.room

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zhh.app.navigation.demo.model.User
import com.zhh.app.navigation.demo.room.AppDatabase.Companion.getInstance

/**
 * Created by zhanghai on 2020/5/19.
 * function：
 */
class UserRepository(context: Context?) {
    private val mAppDB: AppDatabase?
    private val mUserDao: UserDao?
    var userLive: LiveData<List<User>>

    init {
        mAppDB = getInstance(context!!)
        mUserDao = mAppDB!!.userDao
        userLive = mUserDao!!.getUsers()
    }

    fun insertUser(user: User){
        InsertAsyncTask().execute(user)
    }

    private inner class InsertAsyncTask() : AsyncTask<User,Void?,Void?>(){
        override fun doInBackground(vararg p0: User?): Void? {
            mUserDao!!.insertUser(p0[0])
            return null
        }
    }
}