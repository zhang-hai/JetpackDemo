package com.zhh.app.navigation.demo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by zhanghai on 2020/5/18.
 * function：
 */
@Entity(tableName = "user")
class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id : Int = 0;
    @ColumnInfo
    var name : String ? = null
    @ColumnInfo
    var password : String ? = null

    @ColumnInfo
    var status : Int = 1

    constructor(name: String,password:String){
        this.name = name
        this.password = password
    }

}