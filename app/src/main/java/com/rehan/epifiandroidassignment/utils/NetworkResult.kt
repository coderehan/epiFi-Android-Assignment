package com.rehan.epifiandroidassignment.utils

class NetworkResult<out T>(val status: Status, val data: T?, message: String?) {

    companion object {
        fun <T> success(data: T?): NetworkResult<T> {
            return NetworkResult(Status.SUCCESS, data, null)
        }

        fun <T> loading(message: String?): NetworkResult<T> {
            return NetworkResult(Status.LOADING, null, message)
        }

        fun <T> error(message: String?): NetworkResult<T> {
            return NetworkResult(Status.ERROR, null, message)
        }
    }
}

enum class Status {
    SUCCESS,
    LOADING,
    ERROR
}