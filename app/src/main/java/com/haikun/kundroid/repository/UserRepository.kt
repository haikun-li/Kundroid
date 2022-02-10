package com.haikun.kundroid.repository

import com.haikun.kundroid.dao.UserDao
import com.haikun.kundroid.data.Resource
import com.haikun.kundroid.data.UserConstant
import com.haikun.kundroid.data.response.LoginData
import com.haikun.kundroid.data.response.UserInfoBean
import com.haikun.kundroid.request.RetrofitService
import com.haikun.kundroid.request.exeRequestFlow
import com.haikun.kundroid.utils.LogUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * 描述:
 * 创建人: haikun
 * 创建时间: 2021/12/20 16:32
 */
class UserRepository @Inject constructor(
    private val mService: RetrofitService,
    private val userDao: UserDao
) {


    suspend fun login(username: String, password: String): Flow<Resource<LoginData>> {
        return exeRequestFlow(api = {
            mService.login(username, password)
        })
    }

    suspend fun register(
        username: String,
        password: String,
        rePassword: String
    ): Flow<Resource<Any>> {
        return exeRequestFlow(api = {
            mService.register(username, password, rePassword)
        })
    }

    suspend fun userInfo(): Flow<Resource<UserInfoBean>> {
        return exeRequestFlow(api = {
            mService.userinfo()
        }, saveToDb = {
            LogUtil.e(it)
            userDao.insertUserInfo(it.userInfo)
            userDao.insertCoinInfo(it.coinInfo)
        })
    }

    suspend fun userInfoFromDb(): UserInfoBean {
        val queryUserInfoAndCoinInfo = userDao.queryUserInfoAndCoinInfo(UserConstant.userId)
        val firstOrNull = queryUserInfoAndCoinInfo.entries.firstOrNull()
        LogUtil.e(queryUserInfoAndCoinInfo)
        LogUtil.e(firstOrNull)
        firstOrNull?.apply {
            return UserInfoBean(value,key)
        }
        return UserInfoBean()
    }


}