package com.haikun.kundroid.request

import com.haikun.kundroid.data.AppResponse
import com.haikun.kundroid.data.response.ArticleList
import com.haikun.kundroid.data.response.LoginData
import com.haikun.kundroid.data.response.UserInfoBean
import retrofit2.http.*

interface RetrofitService {

    @POST("user/register")
    @FormUrlEncoded
    suspend fun register(@Field("username") userName:String,@Field("password") password:String,@Field("repassword") repassword:String): AppResponse<Any>

    @POST("user/login")
    @FormUrlEncoded
    suspend fun login(@Field("username") userName:String,@Field("password") password:String): AppResponse<LoginData>

    @GET("user/lg/userinfo/json")
    suspend fun userinfo(): AppResponse<UserInfoBean>

    @GET("article/list/{page}/json")
    suspend fun articleList(@Path("page") page: Int): AppResponse<ArticleList>
}