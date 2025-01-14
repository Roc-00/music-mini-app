package com.example.wangyiyun.core.model

import com.example.wangyiyun.core.datastore.SessionPreferences
import kotlinx.serialization.Serializable

/**
 * 登录成功后，返回的信息
 */
@Serializable
data class Session(
    /**
     * 用户Id
     */
    val userId: String,

    /**
     * 登录后的Session
     */
    val session: String,

    /**
     * 聊天token
     */
    val chatToken: String,

    /**
     * 登录后直接返回用户信息，避免再次请求提高效率
     */
    val user: User,
) {
//    保存数据的方法
    fun toPreferences(): SessionPreferences? {
        return SessionPreferences.newBuilder()
            .setUserId(userId)
            .setSession(session)
            .setChatToken(chatToken)
            .build()
    }
}