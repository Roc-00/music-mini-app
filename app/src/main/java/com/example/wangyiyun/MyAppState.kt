package com.example.wangyiyun

import com.example.wangyiyun.core.database.MyDatabase

/**
 * 全局界面以外状态
 */
object MyAppState {
    var session: String = ""
    var userId: String = ""
    var myDatabase: MyDatabase? = null
}