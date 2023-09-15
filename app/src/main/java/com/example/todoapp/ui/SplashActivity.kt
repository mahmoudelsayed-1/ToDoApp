package com.example.todoapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.todoapp.R
import com.example.todoapp.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startSplashActivity()
    }

    private fun startSplashActivity() {

        Handler(Looper.getMainLooper()).postDelayed({

         val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

        },2000)

    }
}