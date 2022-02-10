package com.haikun.kundroid.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.haikun.kundroid.data.response.UserInfoBean

/**
 * 描述:
 * 创建人: haikun
 * 创建时间: 2022/1/19 11:01
 */
@Dao
abstract class UserDao {

    @Insert(onConflict = REPLACE)
    abstract suspend fun insertUserInfo(userInfo: UserInfoBean.UserInfo)

    @Query("select * from UserInfo where id = :userId")
    abstract suspend fun queryUserInfo(userId: Int): UserInfoBean.UserInfo

    @Insert(onConflict = REPLACE)
    abstract suspend fun insertCoinInfo(coinInfo: UserInfoBean.CoinInfo)

    @Query("select * from CoinInfo where userId = :userId")
    abstract suspend fun queryCoinInfo(userId: Int): UserInfoBean.CoinInfo

    @Query("select * from CoinInfo left join UserInfo on UserInfo.id=CoinInfo.userId and UserInfo.id=:userId")
    abstract suspend fun queryUserInfoAndCoinInfo(userId: Int): Map<UserInfoBean.UserInfo, UserInfoBean.CoinInfo>

}