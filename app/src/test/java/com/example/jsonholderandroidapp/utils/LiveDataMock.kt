package com.example.jsonholderandroidapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

object LiveDataMock {
    fun <T> getReponse(liveData: LiveData<T>): T {
        val result = arrayOfNulls<Any>(1);
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T) {
                result[0] = t
                latch.countDown()
                liveData.removeObserver(this)
            }
        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        return result[0] as T
    }
}