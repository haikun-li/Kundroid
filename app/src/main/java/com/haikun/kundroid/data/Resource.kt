package com.haikun.kundroid.data


sealed class Resource<T> {
    class SuccessResource<T>(val data: T?) : Resource<T>() {
        override fun toString(): String {
            return "SuccessResource(data=$data)"
        }
    }

    class ErrorResource<T>(val msg: String, val code: Int, val data: T?) : Resource<T>() {
        override fun toString(): String {
            return "ErrorResource(msg='$msg', code=$code)"
        }
    }

    class LoadingResource<T>(val data: T?) : Resource<T>() {
        override fun toString(): String {
            return "LoadingResource(data=$data)"
        }
    }

    class EmptyResource<T> : Resource<T>()
}