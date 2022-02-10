package com.haikun.kundroid.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.haikun.kundroid.data.response.UserInfoBean

/**
 * 描述:
 * 创建人: haikun
 * 创建时间: 2022/1/19 10:38
 */
@Database(
    entities = [UserInfoBean.UserInfo::class, UserInfoBean.CoinInfo::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}
