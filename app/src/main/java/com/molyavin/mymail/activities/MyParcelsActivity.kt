package com.molyavin.mymail.activities

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.molyavin.mymail.MenuActivity
import com.molyavin.mymail.R
import com.molyavin.mymail.database.DataBaseMyParcel
import com.molyavin.mymail.databinding.ActivityMyParcelsBinding
import com.molyavin.mymail.utis.NetworkChangeListener

class MyParcelsActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMyParcelsBinding
    private val networkChangeListener: NetworkChangeListener = NetworkChangeListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyParcelsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val db = DataBaseMyParcel(binding)
        db.readDataBase()

        checkParcel()


        binding.swipeRefreshLayout.setOnRefreshListener {
            db.readDataBase()
            binding.swipeRefreshLayout.isRefreshing = false
        }


        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            overridePendingTransition(R.anim.slidein, R.anim.slideout)
        }
    }


    private fun checkParcel(){
        if (DataBaseMyParcel.ttnCount > 0){
            binding.textParcelIsEmpty.visibility = View.INVISIBLE
        }else if (DataBaseMyParcel.ttnCount <= 0){
            binding.textParcelIsEmpty.visibility = View.VISIBLE
        }
    }

    override fun onStart() {
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeListener,filter)
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        checkParcel()
    }

    override fun onStop() {
        unregisterReceiver(networkChangeListener)
        super.onStop()
    }
}