package com.haikun.kundroid.utils

import com.squareup.moshi.Moshi

inline fun <reified T> toJson(data: T): String? {
    return Moshi.Builder().build().adapter(T::class.java).toJson(data)
}

inline fun <reified T> fromJson(json: String): T? {
    return Moshi.Builder().build().adapter(T::class.java).fromJson(json)
}