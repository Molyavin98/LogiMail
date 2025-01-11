package com.ultimatekillaa.logimail.activities

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ultimatekillaa.logimail.MenuActivity
import com.ultimatekillaa.logimail.R
import com.ultimatekillaa.logimail.databinding.ActivitySupportCenterBinding
import com.ultimatekillaa.logimail.utis.NetworkChangeListener

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