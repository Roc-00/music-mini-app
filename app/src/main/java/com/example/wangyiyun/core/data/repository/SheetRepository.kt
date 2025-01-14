package com.example.wangyiyun.core.data.repository

import com.example.wangyiyun.core.model.Sheet
import com.example.wangyiyun.core.model.response.NetworkResponse
import com.example.wangyiyun.core.network.datasource.MyNetworkDatasource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * 歌单仓库
 */
class SheetRepository @Inject constructor(
    private val networkResponse: MyNetworkDatasource,
) {
    fun sheetDetail(
        id : String,
    ): Flow<NetworkResponse<Sheet>> = flow{
        emit(
            networkResponse.sheetDetail(id)
        )
    }.flowOn(Dispatchers.IO)
}