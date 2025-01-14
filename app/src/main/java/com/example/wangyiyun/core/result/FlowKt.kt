package com.example.wangyiyun.core.result

import com.example.wangyiyun.core.model.response.NetworkResponse
import com.example.wangyiyun.core.exception.CommonException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

/**
 * 添加asResult扩展函数，将Flow<T> 转换为  Flow<Result<T>>
 *
 * Flow<T>：当你使用 Flow<T> 时，如果发生错误，默认情况下这些错误会被抛出并终止 Flow 的执行。要捕获这些错误，你需要额外添加 catch 操作符。
 * Flow<Result<T>>：通过将每个发射项包装在 Result<T> 中，你可以在每个步骤中显式地处理成功或失败情况。Result 类型本身可以携带成功值或异常信息，使得错误处理逻辑更加清晰和一致。
 */
fun <T> Flow<T>.asResult(): Flow<Result<T>> = map {
    if (it is NetworkResponse<*>){
        if (it.isSucceeded){
            Result.success(it)
        }else{
            Result.failure((CommonException(it)))
        }
    }else{
        Result.success(it)
    }
}.onStart {

}.catch {
    emit(Result.failure(it))
}