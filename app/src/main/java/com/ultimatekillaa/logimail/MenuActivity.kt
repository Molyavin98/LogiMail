package com.ultimatekillaa.logimail

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.ultimatekillaa.logimail.activities.*
import com.ultimatekillaa.logimail.database.DataBaseAuth
import com.ultimatekillaa.logimail.databinding.ActivityMenuBinding
import com.ultimatekillaa.logimail.utis.NetworkChangeListener

@Suppress("DEPRECATION")
class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private val networkChangeListener: NetworkChangeListener = NetworkChangeListener()
    private lateinit var dB: DataBaseAuth
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()
        val userId: String = mAuth.uid.toString()

        dB = DataBaseAuth(userId)
        dB.readDataBase()

        onClickListener()
    }

    private fun onClickListener() {

        binding.btnSettingProf.setOnClickListener {
            startActivity(Intent(this, ProfileSettingActivity::class.java))
            overridePendingTransition(R.anim.slideinback, R.anim.slideoutback)
        }

        binding.btnMyParcel.setOnClickListener {
            startActivity(Intent(this, MyParcelsActivity::class.java))
            overridePendingTransition(R.anim.slideinback, R.anim.slideoutback)
        }

        binding.btnMap.setOnClickListener {
            startActivity(Intent(this, RoadMapActivity::class.java))
            overridePendingTransition(R.anim.slideinback, R.anim.slideoutback)
        }


        binding.btnSupportCenter.setOnClickListener {
            startActivity(Intent(this, SupportCenterActivity::class.java))
            overridePendingTransition(R.anim.slideinback, R.anim.slideoutback)
        }

        binding.btnCreateParcel.setOnClickListener {
            startActivity(Intent(this, CreatingParcelActivity::class.java))
            overridePendingTransition(R.anim.slidein, R.anim.slideout)
        }

        binding.btnCallCourier.setOnClickListener {
            startActivity(Intent(this, CallDeliveryGayActivity::class.java))
            overridePendingTransition(R.anim.slidein, R.anim.slideout)
        }


        binding.btnResponse.setOnClickListener {
            startActivity(Intent(this, ResponseActivity::class.java))
            overridePendingTransition(R.anim.slidein, R.anim.slideout)
        }
    }

    override fun onStart() {
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeListener, filter)
        super.onStart()
    }

    override fun onStop() {
        unregisterReceiver(networkChangeListener)
        super.onStop()
    }

    @Deprecated(message = "Deprecated in Java")
    override fun onBackPressed() {}
}