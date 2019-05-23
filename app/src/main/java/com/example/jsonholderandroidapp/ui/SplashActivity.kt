package com.example.jsonholderandroidapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.jsonholderandroidapp.R
import com.example.jsonholderandroidapp.ui.album.HomeActivity

class SplashActivity : AppCompatActivity() {

    private val _splashTimeOut = 500L;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startHomeActivity()
        }, _splashTimeOut)
    }

    private fun startHomeActivity() {
        val homeIntent = Intent(this, HomeActivity::class.java)
        startActivity(homeIntent)
        finish()
    }
}