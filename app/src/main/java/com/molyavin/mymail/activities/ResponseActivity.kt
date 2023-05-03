package com.molyavin.mymail.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.textfield.TextInputLayout
import com.molyavin.mymail.MenuActivity
import com.molyavin.mymail.R
import com.molyavin.mymail.utis.NetworkChangeListener

class ResponseActivity : AppCompatActivity() {

    private lateinit var btnSendResponse:Button
    private lateinit var btnBack: ImageButton
    private val networkChangeListener: NetworkChangeListener = NetworkChangeListener()
    private lateinit var fieldResponse:TextInputLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_response)

        btnSendResponse = findViewById(R.id.btnSendResponse)
        btnBack = findViewById(R.id.btnBack)
        fieldResponse = findViewById(R.id.fieldResponse)

        onClickListener()
    }

    private fun onClickListener(){
        btnSendResponse.setOnClickListener {

            if (fieldResponse.editText?.text.toString().isEmpty()){
                Toast.makeText(this,getString(R.string.text_toast_field_is_not_empty),Toast.LENGTH_SHORT).show()
            }else {
                fieldResponse.editText?.text?.clear()
                Toast.makeText(this, getString(R.string.text_toast_thanks_for_response), Toast.LENGTH_SHORT).show()
            }
        }

        btnBack.setOnClickListener {
            onBackPressed()
            overridePendingTransition(R.anim.slideinback, R.anim.slideoutback)
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