package com.haikun.kundroid.data


data class AppResponse<T>(val data:T?, val errorCode:Int, val errorMsg:String)