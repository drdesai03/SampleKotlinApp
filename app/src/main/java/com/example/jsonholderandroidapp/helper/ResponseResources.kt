package com.example.jsonholderandroidapp.helper

import com.example.jsonholderandroidapp.utils.Status

data class ResponseResources<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): ResponseResources<T> {
            return ResponseResources(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): ResponseResources<T> {
            return ResponseResources(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): ResponseResources<T> {
            return ResponseResources(Status.LOADING, data, null)
        }
    }
}