package com.example.wangyiyun.core.network.datasource

import com.example.wangyiyun.core.model.BaseId
import com.example.wangyiyun.core.model.Session
import com.example.wangyiyun.core.model.Sheet
import com.example.wangyiyun.core.model.Song
import com.example.wangyiyun.core.model.User
import com.example.wangyiyun.core.model.ViewData
import com.example.wangyiyun.core.model.response.NetworkPageData
import com.example.wangyiyun.core.model.response.NetworkResponse
import retrofit2.http.Body
import retrofit2.http.Query

interface MyNetworkDatasource {
    suspend fun songs(): NetworkResponse<NetworkPageData<Song>>

    suspend fun songDetail(
        @Query(value = "id") id: String,
    ): NetworkResponse<Song>

    suspend fun indexes(app: Int): NetworkResponse<NetworkPageData<ViewData>>

    suspend fun sheetDetail(id: String): NetworkResponse<Sheet>

    /**
     * 登录
     */
    suspend fun login(
        data: User,
    ): NetworkResponse<Session>

    suspend fun register(
        @Body data: User,
    ): NetworkResponse<BaseId>
}