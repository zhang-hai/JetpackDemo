package com.zhh.app.navigation.demo.model

/**
 * Created by zhanghai on 2020/5/20.
 * function：通讯录头部
 */
class ContactHeaderBean(iconId: Int, bgcolor: Int, name: String) {
    var iconId: Int = 0
    var bgcolor: Int = 0
    var name: String? = null

    init {
        this.iconId = iconId
        this.bgcolor = bgcolor
        this.name = name
    }


}