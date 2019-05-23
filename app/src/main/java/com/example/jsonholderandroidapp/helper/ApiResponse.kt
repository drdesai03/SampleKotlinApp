package com.example.jsonholderandroidapp.helper

import retrofit2.Response

sealed class ApiResponse<T> {

    companion object {

        fun <T> create(error: Throwable) : ApiErrorResponse<T> {
            return ApiErrorResponse(error.message ?: "Unknown Error")
        }

        //This is for successful api response
        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(
                        body = body
                    )
                }
            } else {
                val msg = response.errorBody()?.string()
                val errorMessage = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                ApiEmptyResponse()
            }
        }
    }
}

class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>() {

}

data class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()
