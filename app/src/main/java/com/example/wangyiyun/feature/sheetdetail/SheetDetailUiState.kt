package com.example.wangyiyun.feature.sheetdetail

import com.example.wangyiyun.core.model.Sheet
import com.example.wangyiyun.core.exception.CommonException

/**
 * 歌单详情界面状态
 */
sealed interface SheetDetailUiState {
    /**
     * 成功
     */
    data class Success(val data: Sheet) : SheetDetailUiState

    /**
     * 加载中
     */
    data object Loading : SheetDetailUiState

    data class Error(
        val exception: CommonException,
    ) : SheetDetailUiState
}