package com.ultimatekillaa.logimail.activities

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ultimatekillaa.logimail.R
import com.ultimatekillaa.logimail.databinding.ActivityPaymentBinding
import com.ultimatekillaa.logimail.utis.NetworkChangeListener

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