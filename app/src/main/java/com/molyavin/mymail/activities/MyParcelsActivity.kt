package com.molyavin.mymail.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.molyavin.mymail.MenuActivity
import com.molyavin.mymail.databinding.ActivityMyParcelsBinding

class MyParcelsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyParcelsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyParcelsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnBack.setOnClickListener {
            startActivity(Intent(this,MenuActivity::class.java))
        }
    }
}