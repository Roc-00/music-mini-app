package com.example.wangyiyun.feature.loginhome

/**
 * 登录界面状态
 */
sealed interface LoginHomeUiState {
    data object Success : LoginHomeUiState
    data object Loading : LoginHomeUiState
    data object None : LoginHomeUiState
}