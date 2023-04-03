package com.molyavin.mymail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import com.molyavin.mymail.activities.*
import com.molyavin.mymail.database.DataBaseAuth
import com.molyavin.mymail.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var dB: DataBaseAuth
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        mAuth = FirebaseAuth.getInstance()
        val userId: String = mAuth.uid.toString()

        dB = DataBaseAuth(userId)
        dB.readDataBase()


        onClickListener()

    }

    private fun onClickListener(){

        binding.btnSettingProf.setOnClickListener {
            startActivity(Intent(this, ProfileSettingActivity::class.java))
        }

        binding.btnSupportCenter.setOnClickListener {
            startActivity(Intent(this, SupportCenterActivity::class.java))
        }

        binding.btnCallCourier.setOnClickListener {
            startActivity(Intent(this, CallDeliveryGayActivity::class.java))
        }

        binding.btnCreateParcel.setOnClickListener {
            startActivity(Intent(this,CreatingParcelActivity::class.java))
        }

        binding.btnMap.setOnClickListener {
            startActivity(Intent(this,RoadMapActivity::class.java))
        }

        binding.btnMyParcel.setOnClickListener {
            startActivity(Intent(this,MyParcelsActivity::class.java))
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {}
}