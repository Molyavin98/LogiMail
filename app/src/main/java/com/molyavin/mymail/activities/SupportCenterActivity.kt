package com.molyavin.mymail.activities

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.molyavin.mymail.MenuActivity
import com.molyavin.mymail.R
import com.molyavin.mymail.databinding.ActivitySupportCenterBinding
import com.molyavin.mymail.utis.NetworkChangeListener

class SupportCenterActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySupportCenterBinding
    private val networkChangeListener: NetworkChangeListener = NetworkChangeListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupportCenterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            overridePendingTransition(R.anim.slidein, R.anim.slideout)
        }

        binding.btnCallSupport.setOnClickListener {
            Toast.makeText(this, getString(R.string.text_toast_wait_to_call), Toast.LENGTH_LONG)
                .show()
        }
    }


    override fun onStart() {
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeListener,filter)
        super.onStart()
    }

    override fun onStop() {
        unregisterReceiver(networkChangeListener)
        super.onStop()
    }
}