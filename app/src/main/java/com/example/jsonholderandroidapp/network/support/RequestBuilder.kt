package com.example.jsonholderandroidapp.network.support

import androidx.lifecycle.LiveData
import com.example.jsonholderandroidapp.helper.ApiResponse
import com.example.jsonholderandroidapp.utils.AppExecutors
import java.util.concurrent.TimeUnit

data class RequestBuilder<RES>(
    val keyword: String,
    val executors: AppExecutors,
    val dbSource: LiveData<RES>? = null,
    val rateLimiter: RateLimiter<String> = RateLimiter(5, TimeUnit.SECONDS),
    val apiResponse: LiveData<ApiResponse<RES>>?,
    val shouldSaveResultToDb: Boolean = false
)