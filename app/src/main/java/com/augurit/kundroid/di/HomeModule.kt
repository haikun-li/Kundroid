package com.augurit.kundroid.di

import com.augurit.kundroid.request.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * 包名: com.augurit.kundroid.di
 * 文件描述: TODO
 * 创建人: haikun
 * 创建时间: 2021/12/16 15:39
 * 修改人: haikun
 * 修改时间: 2021/12/16 15:39
 * 修改备注: TODO
 */
@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }
}
