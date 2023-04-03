package com.molyavin.mymail.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.molyavin.mymail.MenuActivity
import com.molyavin.mymail.database.DataBaseAuth
import com.molyavin.mymail.databinding.ActivityProfileSettingBinding

class ProfileSettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileSettingBinding
    private lateinit var mSettings: SharedPreferences


    @SuppressLint("CommitPrefEdits", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        mSettings = getSharedPreferences(USER_DATA_AUTH, Context.MODE_PRIVATE)

        binding.textUserInfo.text =  "${DataBaseAuth.fullName}\n${DataBaseAuth.phone}"

        onClickListener()
    }

    private fun onClickListener(){

        binding.exitFromAccount.setOnClickListener {
            exitFromProfile()
        }

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

    }

    private fun exitFromProfile(){
        val editor: SharedPreferences.Editor = mSettings.edit()
        editor.putString(EMAIL_AUTH,null)
        editor.putString(PASSWORD_AUTH,null)
        editor.putString(USER_ID,null)
        editor.apply()

        val intent = Intent(this, AuthorizationActivity::class.java)
        startActivity(intent)
    }

}