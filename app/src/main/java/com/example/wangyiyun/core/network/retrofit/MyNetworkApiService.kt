package com.example.wangyiyun.core.network.retrofit

import com.example.wangyiyun.core.model.BaseId
import com.example.wangyiyun.core.model.Session
import com.example.wangyiyun.core.model.Sheet
import com.example.wangyiyun.core.model.Song
import com.example.wangyiyun.core.model.User
import com.example.wangyiyun.core.model.ViewData
import com.example.wangyiyun.core.model.response.NetworkPageData
import com.example.wangyiyun.core.model.response.NetworkResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * 网络请求接口
 */
interface MyNetworkApiService {
    //region 音乐
    @GET("v1/songs/page")
    suspend fun songs(): NetworkResponse<NetworkPageData<Song>>

    @GET("v1/songs/info")
    suspend fun songDetail(
        @Query(value = "id") id: String,
    ): NetworkResponse<Song>
    //endregion

    /**
     * 首页列表
     */
    @GET("v1/indexes")
    suspend fun indexes(@Query(value = "app") app: Int): NetworkResponse<NetworkPageData<ViewData>>

    /**
     * 歌单详情
     */
    @GET("v1/sheets/info")
    suspend fun sheetDetail(@Query(value = "id") id: String): NetworkResponse<Sheet>

    /**
     * 登录
     */
    @POST("v1/login")
    suspend fun login(
        @Body data: User,
    ): NetworkResponse<Session>

    @POST("v1/users/add")
    suspend fun register(
        @Body data: User,
    ): NetworkResponse<BaseId>
}