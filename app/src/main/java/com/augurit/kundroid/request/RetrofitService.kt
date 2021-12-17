package com.augurit.kundroid.request

import com.augurit.kundroid.data.AppResponse
import com.augurit.kundroid.data.response.Content
import com.augurit.kundroid.data.response.User
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("getUser")
    suspend fun getUser(@Query("page") page: Int): AppResponse<List<User>>

    @GET("getContent")
    suspend fun getContentByTitle(@Query("page") page: Int, @Query("title") title: String): AppResponse<List<Content>>
}