package com.zhh.app.navigation.demo.model

/**
 * Created by zhanghai on 2020/5/20.
 * function：
 */
class FindItemBean(iconId:Int,name:String ,isAlone:Boolean,isLast:Boolean) {
    var iconId:Int = 0          //图标id
    var name:String ? = null    //名称
    var isAlone:Boolean = false //是否是单个
    var isLast:Boolean = false  //是否是最后一个

    init {
        this.iconId = iconId
        this.name = name
        this.isAlone = isAlone
        this.isLast = isLast
    }
}