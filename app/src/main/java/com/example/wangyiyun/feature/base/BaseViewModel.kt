package com.example.wangyiyun.feature.base

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * 通用VM
 */
open class BaseViewModel : ViewModel() {
    var developState: MutableStateFlow<DevelopUiState> =
        MutableStateFlow<DevelopUiState>(DevelopUiState.Finished) // 功能开发完成状态

    fun setDevelopingUiState() {
        developState.value = DevelopUiState.UnFinished
    }

    fun setDevelopedUiState() {
        developState.value = DevelopUiState.Finished
    }

    fun unfinishedFunClick(){
        setDevelopingUiState()
        var timer: CountDownTimer = object : CountDownTimer(1000, 3000) {
            override fun onTick(p0: Long) {

            }

            /**
             * 倒计时结束
             */
            override fun onFinish() {
                setDevelopedUiState()
            }

        }.start()
    }
}