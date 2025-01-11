package com.ultimatekillaa.logimail.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.ultimatekillaa.logimail.MenuActivity
import com.ultimatekillaa.logimail.R
import com.ultimatekillaa.logimail.databinding.ActivityRoadMapBinding
import com.ultimatekillaa.logimail.utis.NetworkChangeListener

class RoadMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityRoadMapBinding
    private val networkChangeListener: NetworkChangeListener = NetworkChangeListener()
    private lateinit var mMap: GoogleMap

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoadMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            overridePendingTransition(R.anim.slidein, R.anim.slideout)
        }

    }


    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

        val markers = arrayOf(
            MarkerOptions().position(LatLng(50.464853, 30.638051))
                .title(getString(R.string.street_1)),
            MarkerOptions().position(LatLng(50.435544, 30.640831))
                .title(getString(R.string.street_2)),
            MarkerOptions().position(LatLng(50.418739, 30.550657))
                .title(getString(R.string.street_3)),
            MarkerOptions().position(LatLng(50.397987, 30.639219))
                .title(getString(R.string.street_4)),
            MarkerOptions().position(LatLng(50.514844, 30.459023))
                .title(getString(R.string.street_5)),
            MarkerOptions().position(LatLng(50.467853, 30.376333))
                .title(getString(R.string.street_6)),
            MarkerOptions().position(LatLng(50.468045, 30.515490))
                .title(getString(R.string.street_7)),
            MarkerOptions().position(LatLng(50.448776, 30.486067))
                .title(getString(R.string.street_8)),
            MarkerOptions().position(LatLng(50.455494, 30.429349))
                .title(getString(R.string.street_9)),
            MarkerOptions().position(LatLng(50.403856, 30.664798))
                .title(getString(R.string.street_10)),
            MarkerOptions().position(LatLng(50.399798, 30.615901))
                .title(getString(R.string.street_11)),
            MarkerOptions().position(LatLng(50.463421, 30.611700))
                .title(getString(R.string.street_12)),
            MarkerOptions().position(LatLng(50.438707, 30.409891))
                .title(getString(R.string.street_13)),
            MarkerOptions().position(LatLng(50.433514, 30.432577))
                .title(getString(R.string.street_14)),
            MarkerOptions().position(LatLng(50.421833, 30.470290))
                .title(getString(R.string.street_15)),
            MarkerOptions().position(LatLng(50.397748, 30.489232))
                .title(getString(R.string.street_16)),
        )

        for (marker in markers) {
            mMap.addMarker(marker)
        }

        val builder = LatLngBounds.Builder()
        for (marker in markers) {
            builder.include(marker.position)
        }
        val bounds = builder.build()

        val cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 100)
        googleMap.animateCamera(cameraUpdate)

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