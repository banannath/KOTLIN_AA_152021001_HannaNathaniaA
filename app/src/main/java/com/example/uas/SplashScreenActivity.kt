package com.example.uas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.example.uas.LoginActivity
import retrofit2.Call
import retrofit2.Response

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val logo = findViewById<ImageView>(R.id.logo)
        logo.alpha = 0f
        logo.animate().setDuration(1500).alpha(1f).withEndAction {
            val i = Intent(this,LoginActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }
}