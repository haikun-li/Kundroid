package com.haikun.kundroid.request


import com.haikun.kundroid.data.AppResponse
import com.haikun.kundroid.data.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.ConnectException
import java.net.SocketTimeoutException


private typealias loadFromDb<T> = suspend () -> T?
private typealias saveToDb<T> = suspend (data: T) -> Unit
typealias api<T> = suspend () -> AppResponse<T>

suspend fun <T> exeRequest(
    api: api<T>,
    loadFromDb: loadFromDb<T>? = null,
    saveToDb: saveToDb<T>? = null
): Resource<T> {
    try {
        val response = api()
        return if (response.errorCode == 0) {
            if (response.data != null) {
                saveToDb?.invoke(response.data)
            }
            Resource.SuccessResource(response.data)
        } else {
            Resource.ErrorResource(response.errorMsg, response.errorCode, loadFromDb?.invoke())
        }
    } catch (e: Exception) {
        e.printStackTrace()
        val msg = when (e) {
            is SocketTimeoutException -> {
                "请求超时"
            }
            is ConnectException -> {
                "网络异常"
            }
            else -> {
                "请求出错"
            }
        }
        return Resource.ErrorResource(msg, -1, loadFromDb?.invoke())
    }
}


fun <T> exeRequestFlow(
    loadFromDb: loadFromDb<T>? = null,
    saveToDb: saveToDb<T>? = null,
    api: api<T>,
    ): Flow<Resource<T>> {
    return flow {
        emit(Resource.LoadingResource(loadFromDb?.invoke()))
        emit(exeRequest(api, loadFromDb, saveToDb))
    }

}