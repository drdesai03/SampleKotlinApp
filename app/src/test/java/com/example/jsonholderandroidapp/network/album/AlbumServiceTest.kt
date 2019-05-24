package com.example.jsonholderandroidapp.network.album

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AlbumServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var albumService: AlbumService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createAlbumService() {
        mockWebServer = MockWebServer()
    }

    @After
    fun disposeAlbumService() {

    }
}