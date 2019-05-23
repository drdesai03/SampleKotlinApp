package com.example.jsonholderandroidapp.helper

import androidx.annotation.MainThread
import androidx.lifecycle.MediatorLiveData
import com.example.jsonholderandroidapp.utils.AppExecutors

abstract class NetworkResponse<REQ, RES>
@MainThread constructor(executor: AppExecutors) {

    private val result = MediatorLiveData<ResponseResources<RES>>()



}