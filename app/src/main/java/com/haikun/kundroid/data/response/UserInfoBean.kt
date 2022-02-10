package com.haikun.kundroid.data.response

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

data class UserInfoBean(
    val coinInfo: CoinInfo = CoinInfo(),
    val userInfo: UserInfo = UserInfo()
) {
    @Entity
    data class CoinInfo(
        var coinCount: Int = 0,
        var level: Int = 0,
        var nickname: String = "",
        var rank: String = "",
        @PrimaryKey
        var userId: Int = 0,
        var username: String = ""
    )

    @Entity
    data class UserInfo(
        var admin: Boolean = false,
        @Ignore
        val chapterTops: List<Any> = mutableListOf(),
        var coinCount: Int = 0,
        @Ignore
        val collectIds: List<Any> = mutableListOf(),
        var email: String = "",
        var icon: String = "",
        @PrimaryKey
        var id: Int = 0,
        var nickname: String = "",
        var password: String = "",
        var publicName: String = "",
        var token: String = "",
        var type: Int = 0,
        var username: String = ""
    )
}