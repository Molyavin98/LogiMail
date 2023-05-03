package com.molyavin.mymail.activities

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.molyavin.mymail.R
import com.molyavin.mymail.databinding.ActivityPaymentBinding
import com.molyavin.mymail.utis.NetworkChangeListener

class PaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding
    private val networkChangeListener: NetworkChangeListener = NetworkChangeListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ttnText.text = intent.getStringExtra("ttn_text")

        onClickListener()

    }

    private fun onClickListener() {

        binding.btnClose.setOnClickListener { onBackPressed() }

        binding.btnGooglePay.setOnClickListener { paymentOnline() }
        binding.btnApplePay.setOnClickListener { paymentOnline() }
        binding.btnCryptoPay.setOnClickListener { paymentOnline() }
    }


    private fun paymentOnline() {
        Toast.makeText(
            this,
            getString(R.string.text_toast_payment_is_not_possible),
            Toast.LENGTH_SHORT
        ).show()
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