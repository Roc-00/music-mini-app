package com.example.wangyiyun.core.data.repository


import com.example.wangyiyun.core.model.BaseId
import com.example.wangyiyun.core.model.User
import com.example.wangyiyun.core.model.response.NetworkResponse
import com.example.wangyiyun.core.network.datasource.MyNetworkDatasource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.http.Body
import javax.inject.Inject

/**
 * 用户仓库
 */
class UserRepository @Inject constructor(
    private val networkDataSource: MyNetworkDatasource,
) {
    suspend fun register(
        @Body data: User,
    ): Flow<NetworkResponse<BaseId>> = flow {
        emit(
            networkDataSource.register(data)
        )
    }.flowOn(Dispatchers.IO)

//    fun userDetail(id: String): Flow<NetworkResponse<User>> = flow {
//        emit(
//            networkDataSource.userDetail(id)
//        )
//    }.flowOn(Dispatchers.IO)
//
//    fun updateUser(data: User): Flow<NetworkResponse<BaseModel>> = flow {
//        emit(
//            networkDataSource.updateUser(data)
//        )
//    }.flowOn(Dispatchers.IO)
//
//    fun setPassword(data: User): Flow<NetworkResponse<BaseId>> = flow {
//        emit(
//            networkDataSource.setPassword(data)
//        )
//    }.flowOn(Dispatchers.IO)

}