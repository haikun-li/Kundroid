package com.haikun.kundroid.request

import com.haikun.kundroid.data.AppResponse
import com.haikun.kundroid.data.response.*
import retrofit2.http.*

interface RetrofitService {

    @POST("user/register")
    @FormUrlEncoded
    suspend fun register(
        @Field("username") userName: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): AppResponse<Any>

    @POST("user/login")
    @FormUrlEncoded
    suspend fun login(@Field("username") userName: String, @Field("password") password: String): AppResponse<LoginData>

    @GET("user/lg/userinfo/json")
    suspend fun userinfo(): AppResponse<UserInfoBean>

    @GET("article/list/{page}/json")
    suspend fun articleList(@Path("page") page: Int): AppResponse<ArticleList<ArticleListData>>

    //收藏文章列表
    @GET("lg/collect/list/{page}/json")
    suspend fun collectList(@Path("page") page: Int): AppResponse<ArticleList<ArticleListData>>

    //取消收藏
    @POST("lg/uncollect_originId/{id}/json")
    suspend fun unCollect(@Path("id") id: Int): AppResponse<Any>

    //取消收藏
    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    suspend fun unCollectFromCollect(@Path("id") id: Int, @Field("originId") originId: Int): AppResponse<Any>

    //收藏站内文章
    @POST("lg/collect/{id}/json")
    suspend fun collect(@Path("id") id: Int): AppResponse<Any>

    //收藏站外文章
    @POST("lg/collect/add/json")
    @FormUrlEncoded
    suspend fun collect(@Field("title") title: String, @Field("author") author: String): AppResponse<Any>

    //项目分类
    @GET("project/tree/json")
    suspend fun projectTree(): AppResponse<MutableList<ProjectTree>>

    //项目列表
    @GET("project/list/{page}/json")
    suspend fun projectList(@Path("page") page: Int, @Query("cid") cid: Int): AppResponse<ArticleList<ProjectListData>>

}