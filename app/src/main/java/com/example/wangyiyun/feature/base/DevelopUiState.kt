package com.example.wangyiyun.feature.base

/**
 * 功能是否开发完成
 */
sealed interface DevelopUiState {
    data object Finished : DevelopUiState
    data object UnFinished : DevelopUiState
}