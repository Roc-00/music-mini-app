package com.example.wangyiyun.core.exception

import com.example.wangyiyun.core.model.response.NetworkResponse

/**
 * 全局通用异常
 */
class CommonException(
    /**
     * 网络响应
     */
    val networkResponse: NetworkResponse<*>? = null,

    val throwable: Throwable? = null,

    var tipString: String? = null,
    var tipIcon: Int? = null,
) : RuntimeException()