package com.molyavin.mymail.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.molyavin.mymail.MenuActivity
import com.molyavin.mymail.databinding.ActivitySupportCenterBinding

class SupportCenterActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySupportCenterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupportCenterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding.btnBack.setOnClickListener {
            val intent = Intent(this,MenuActivity::class.java)
            startActivity(intent)
        }

        binding.btnCallSupport.setOnClickListener {
            Toast.makeText(this,"Очікуйте на дзвінок оператора",Toast.LENGTH_LONG).show()
        }
    }
}