package com.example.wangyiyun.feature.login

import com.example.wangyiyun.core.exception.CommonException

sealed interface LoginUiState {
    /**
     * 成功
     */
    data object Success : LoginUiState

    /**
     * 加载中
     */
    data object Loading : LoginUiState

    data object None : LoginUiState

    data class Error(
        val exception: CommonException,
    ) : LoginUiState

    data class ErrorRes(
        val data: Int,
    ) : LoginUiState
}