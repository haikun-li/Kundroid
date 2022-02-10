package com.haikun.kundroid.request

import com.haikun.kundroid.utils.LogUtil
import okhttp3.Interceptor
import okhttp3.Response


/**
 * 描述:
 * 创建人: haikun
 * 创建时间: 2022/1/17 15:27
 */
class CookieInterceptor : Interceptor {

    private var cookie = ""
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (cookie.isNotEmpty()){
            val newBuilder = chain.request().newBuilder()
            newBuilder.addHeader("Cookie",cookie)
            chain.proceed(newBuilder.build())
        }else{
            val originalResponse = chain.proceed(chain.request())
            if (originalResponse.headers("Set-Cookie").isNotEmpty()) {
                val cookies = originalResponse.headers("Set-Cookie")
                val stringBuilder = StringBuilder()
                cookies.forEachIndexed { index, s ->
                    if (index!=0){
                        stringBuilder.append(";")
                    }
                    stringBuilder.append(s)
                }
                cookie=stringBuilder.toString()
            }
            originalResponse
        }
    }
}