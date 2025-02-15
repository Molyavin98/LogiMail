package com.ultimatekillaa.logimail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.ultimatekillaa.logimail.activities.AuthorizationActivity
import com.ultimatekillaa.logimail.activities.EMAIL_AUTH
import com.ultimatekillaa.logimail.activities.PASSWORD_AUTH
import com.ultimatekillaa.logimail.activities.USER_DATA_AUTH
import com.ultimatekillaa.logimail.databinding.ActivitySplashScreenBinding


@SuppressLint("StaticFieldLeak")
private lateinit var binding : ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var mSettings: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mSettings = getSharedPreferences(USER_DATA_AUTH, Context.MODE_PRIVATE)


        val animForImage = AnimationUtils.loadAnimation(this@SplashScreenActivity, R.anim.anim)
        binding.text.animation = animForImage
        animForImage.hasEnded()

        Handler().postDelayed({
            if (mSettings.contains(EMAIL_AUTH) && mSettings.contains(PASSWORD_AUTH)){
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this, AuthorizationActivity::class.java)
                startActivity(intent)
            }
            finish()
        },3000)
    }
}