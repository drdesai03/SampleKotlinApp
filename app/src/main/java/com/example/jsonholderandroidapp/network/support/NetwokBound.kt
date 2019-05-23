package com.example.jsonholderandroidapp.network.support

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.jsonholderandroidapp.helper.ApiEmptyResponse
import com.example.jsonholderandroidapp.helper.ApiErrorResponse
import com.example.jsonholderandroidapp.helper.ApiSuccessResponse
import com.example.jsonholderandroidapp.helper.ResponseResources

class NetwokBound<REQ, RES> @MainThread private constructor(
    private val requestBuilder: RequestBuilder<RES>,
    private val saveResultCallBack: SaveResultCallBack<RES>? = null
) {

    private val result = MediatorLiveData<ResponseResources<RES>>()

    companion object {
        fun <REQ, RES> createNetworkBound(requestBuilder: RequestBuilder<RES>): NetwokBound<REQ, RES> {
            return NetwokBound(requestBuilder)
        }

        fun <REQ, RES> createNetworkBound(
            requestBuilder: RequestBuilder<RES>,
            saveResultCallBack: SaveResultCallBack<RES>
        ): NetwokBound<REQ, RES> {
            return NetwokBound(requestBuilder, saveResultCallBack)
        }
    }

    private fun getResponse() {
        result.value = ResponseResources.loading(null)
        val dbSources = requestBuilder.dbSource!!
        result.addSource(dbSources) { data ->
            result.removeSource(dbSources)
            if (requestBuilder.rateLimiter.shouldFetch(requestBuilder.keyword)) {
                fetchFromNetwork()
            } else {
                result.addSource(dbSources) { newData -> setData(ResponseResources.success(newData)) }
            }
        }
    }

    private fun fetchFromNetwork() {
        val apiRes = requestBuilder.apiResponse!!;

        result.addSource(requestBuilder.dbSource!!) { data ->
            setData(ResponseResources.loading(data))
        }

        result.addSource(apiRes) { response ->
            result.removeSource(requestBuilder.dbSource!!)
            result.removeSource(apiRes)

            when (response) {
                is ApiSuccessResponse -> {
                    requestBuilder.executors.diskIO().execute {
                        saveResultCallBack!!.saveResult(response.body)
                        requestBuilder.executors.mainThread().execute {
                            result.addSource(requestBuilder.dbSource!!) { result ->
                                setData(ResponseResources.success(result))
                            }
                        }
                    }
                }
                is ApiEmptyResponse -> {
                    requestBuilder.executors.mainThread().execute {
                        result.addSource(requestBuilder.dbSource!!) { result ->
                            setData(ResponseResources.success(result))
                        }
                    }
                }
                is ApiErrorResponse -> {
                    //TODO : Reset Ratelimiter
                    result.addSource(requestBuilder.dbSource!!) { result ->
                        setData(
                            ResponseResources.error(
                                response.errorMessage,
                                result
                            )
                        )
                    }
                }
            }
        }
    }

    @MainThread
    private fun setData(newValue: ResponseResources<RES>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    fun asLiveData(): LiveData<ResponseResources<RES>> {
        getResponse()
        return result as LiveData<ResponseResources<RES>>
    }
}

interface SaveResultCallBack<RES> {
    fun saveResult(result: RES)
}