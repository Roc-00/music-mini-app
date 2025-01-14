package com.example.wangyiyun.core.data.repository

import com.example.wangyiyun.core.network.datasource.MyNetworkDatasource
import com.example.wangyiyun.util.Constant
import javax.inject.Inject

class CommonRepository @Inject constructor(
    private val networkDatasource:MyNetworkDatasource,
){
    suspend fun indexes(
        app : Int = Constant.VALUE30
    ) = networkDatasource.indexes(
        app = app
    )
}