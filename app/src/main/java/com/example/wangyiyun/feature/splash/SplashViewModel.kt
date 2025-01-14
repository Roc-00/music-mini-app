package com.example.wangyiyun.feature.splash

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * 启动界面VM
 */
@HiltViewModel
class SplashViewModel @Inject constructor(
) : ViewModel() {
    private var timer: CountDownTimer? = null

    /**
     * 倒计时秒数
     */
    private val _timeLeft = MutableStateFlow(0L)
    val timeLeft: StateFlow<Long> = _timeLeft

    /**
     * 是否跳转到主界面
     */
    val navigateToMain = MutableStateFlow(false)

    init {
        delayToNext(3000)
    }


    private fun delayToNext(time: Long = 3000) {
        timer = object : CountDownTimer(time, 1000) {
            /**
             * 每次倒计时执行
             */
            override fun onTick(millisUntilFinished: Long) {
                _timeLeft.value = millisUntilFinished / 1000 + 1
            }

            /**
             * 倒计时结束
             */
            override fun onFinish() {
                toNext()
            }

        }.start()
    }

    private fun toNext() {
            navigateToMain.value = true
    }

}