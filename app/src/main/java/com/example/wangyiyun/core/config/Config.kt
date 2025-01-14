package com.example.wangyiyun.core.config

import com.example.wangyiyun.BuildConfig

/**
 * 配置文件
 *
 * 例如：API地址，QQ等第三方服务配置信息等
 */
object Config {
    val DEBUG: Boolean = BuildConfig.DEBUG

    /**
     * 端点
     */
    const val ENDPOINT = "https://quick-server-sp.ixuea.com/"
//    const val ENDPOINT = BuildConfig.ENDPOINT

    /**
     * 资源端点
     */
    var RESOURCE_ENDPOINT = "https://com-quick-project.oss-cn-beijing.aliyuncs.com/%s"
//    var RESOURCE_ENDPOINT = BuildConfig.RESOURCE_ENDPOINT

    // 音乐资源
    var RESOURCE_ENDPOINT2 = "http://course-music-dev.ixuea.com/%s"
//    var RESOURCE_ENDPOINT2 = "https://rs.ixuea.com/music/%s"
}