package com.example.wangyiyun

import android.app.Application
import android.util.Log
import com.example.wangyiyun.core.datastore.SessionPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        Log.d(TAG, "onCreate: ")

    }
    /**
     * 登录后初始化
     */
    private var isInitAfterLogin = false
    fun initAfterLogin(session: SessionPreferences) {
        if (isInitAfterLogin) {
            return
        }

        isInitAfterLogin = true
    }
    companion object{
        private const val TAG = "MyApplication"

        lateinit var instance : MyApplication
    }
}