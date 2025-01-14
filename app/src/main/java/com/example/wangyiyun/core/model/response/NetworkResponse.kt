package com.example.wangyiyun.core.model.response

import kotlinx.serialization.Serializable

/**
 * 解析网络响应
 */
@Serializable
data class NetworkResponse<T>(
    /**
     * 真实数据
     * 类型是泛型
     */
    val data: T? = null,

    /**
     * 状态码
     * 等于0表示成功
     */
    val status: Int = 0,

    /**
     * 出错的提示信息
     * 发生了错误不一定有
     */
    val message: String? = null,

    ) {
    /**
     * 是否成功
     *
     * @return
     */
    val isSucceeded: Boolean
        get() = status == 0 //有get()的话每次都会获取最新的status去判断
}