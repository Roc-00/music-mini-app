package com.example.wangyiyun.core.network.datasource


import com.example.wangyiyun.core.config.Config
import com.example.wangyiyun.core.model.BaseId
import com.example.wangyiyun.core.model.Session
import com.example.wangyiyun.core.model.Sheet
import com.example.wangyiyun.core.model.Song
import com.example.wangyiyun.core.model.User
import com.example.wangyiyun.core.model.ViewData
import com.example.wangyiyun.core.model.response.NetworkPageData
import com.example.wangyiyun.core.model.response.NetworkResponse
import com.example.wangyiyun.core.network.retrofit.MyNetworkApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.Query
import javax.inject.Inject

class MyRetrofitDatasource @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
) : MyNetworkDatasource {
    /**
     * 网络请求接口
     */
    private val service = Retrofit.Builder() //创建一个 Retrofit.Builder 实例，这是构建 Retrofit 对象的第一步。
        .baseUrl(Config.ENDPOINT) //设置基础 URL（Base URL）
        .callFactory(okhttpCallFactory) //指定用于执行网络请求的 OkHttpClient 实例。这允许你自定义 HTTP 客户端的行为，比如添加拦截器、设置超时等。
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()) //添加转换工厂（Converter Factory），用于将 HTTP 响应体转换为 Kotlin 或 Java 对象。
        )
        .build() //构建并返回一个 Retrofit 实例。
        .create(MyNetworkApiService::class.java) //使用构建好的 Retrofit 实例来实现 MyNetworkApiService 接口。

    override suspend fun songs(): NetworkResponse<NetworkPageData<Song>> = service.songs()

    override suspend fun songDetail(
        @Query(value = "id") id: String,
    ): NetworkResponse<Song> {
        return service.songDetail(id)
    }

    override suspend fun indexes(app: Int): NetworkResponse<NetworkPageData<ViewData>> = service.indexes(app)

    override suspend fun sheetDetail(id: String): NetworkResponse<Sheet> = service.sheetDetail(id)

    override suspend fun login(data: User): NetworkResponse<Session> = service.login(data)

    override suspend fun register(data: User): NetworkResponse<BaseId> {
        return service.register(data)
    }

}