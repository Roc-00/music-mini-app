package com.example.wangyiyun.core.network.di

import com.example.wangyiyun.core.network.datasource.MyNetworkDatasource
import com.example.wangyiyun.core.network.datasource.MyRetrofitDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * 渠道网络模块
 *
 * 例如：dev环境有很多本地测试数据
 * prod连接线上API
 *
 * 但我们这里没有这种需求，所以就直接放到main目录
 * 参考：https://github.com/search?q=repo%3Aandroid%2Fnowinandroid%20FlavoredNetworkModule&type=code
 */
@Module
@InstallIn(SingletonComponent::class)
interface FlavoredNetworkModule {
    @Binds
    fun binds(impl: MyRetrofitDatasource): MyNetworkDatasource //绑定了 MyRetrofitDatasource 作为 MyNetworkDatasource 接口的具体实现
}