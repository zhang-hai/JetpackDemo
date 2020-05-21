package com.zhh.app.navigation.demo.model

/**
 * Created by zhanghai on 2020/5/19.
 * function：
 */
class ChatItemBean {
    var name:String ?= null
    var content:String? = null

    constructor(name:String,content:String){
        this.name = name
        this.content = content
    }
}