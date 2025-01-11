package com.ultimatekillaa.logimail.activities

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ultimatekillaa.logimail.R
import com.ultimatekillaa.logimail.database.DataBaseAuth
import com.ultimatekillaa.logimail.databinding.ActivityAllParcelInfoBinding
import com.ultimatekillaa.logimail.utis.NetworkChangeListener

class AllParcelInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllParcelInfoBinding
    private val networkChangeListener: NetworkChangeListener = NetworkChangeListener()
    var numberSender: String? = null
    var numberRecipient: String? = null
    private var ttn: String? = null


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllParcelInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        var payment: Boolean? = null
        val number = intent.getStringExtra("number_sender")

        ttn = intent.getStringExtra("ttn")
        numberSender = intent.getStringExtra("number_sender")
        numberRecipient = intent.getStringExtra("number_recipient")

        //TODO: перенести в окрему функцію

        binding.ttnText.text = intent.getStringExtra("ttn")
        binding.fullNameSender.text =
            getString(R.string.text_sender_full_name) + intent.getStringExtra("full_name_sender")
        binding.numberSender.text =
            getString(R.string.text_number) + intent.getStringExtra("number_sender")
        binding.addressSender.text =
            getString(R.string.text_sender_address) + intent.getStringExtra("address_sender")
        binding.fullNameRecipient.text =
            getString(R.string.text_recipient_full_name) + intent.getStringExtra("full_name_recipient")
        binding.numberRecipient.text =
            getString(R.string.text_number) + intent.getStringExtra("number_recipient")
        binding.addressesRecipient.text =
            getString(R.string.text_recipient_address) + intent.getStringExtra("address_recipient")
        binding.typeParcel.text =
            getString(R.string.text_parcel) + intent.getStringExtra("type_parcel")

        payment = intent.getBooleanExtra("payment", false)


        if (!payment) {
            binding.payment.text = getString(R.string.text_payment_sender)
            if (number == DataBaseAuth.phone) {
                binding.btnPayment.visibility = View.VISIBLE
            } else {
                binding.btnPayment.visibility = View.INVISIBLE
            }
        } else {
            binding.payment.text = getString(R.string.text_payment_recipient)
            if (number == DataBaseAuth.phone) {
                binding.btnPayment.visibility = View.INVISIBLE
            } else {
                binding.btnPayment.visibility = View.VISIBLE
            }
        }


        binding.cancelOrder.setOnClickListener {
            cancelParcel()
        }

        binding.btnDone.setOnClickListener {
            onBackPressed()
        }

        binding.btnPayment.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra("ttn_text", ttn)
            startActivity(intent)

        }
    }


    private fun cancelParcel() {

        val listener = DialogInterface.OnClickListener { _, which ->
            when (which) {

                DialogInterface.BUTTON_POSITIVE -> {
                    val dataBase: DatabaseReference =
                        FirebaseDatabase.getInstance().reference.child("parcel")

                    dataBase.child(numberSender.toString()).child(ttn.toString()).removeValue()
                    dataBase.child(numberRecipient.toString()).child(ttn.toString()).removeValue()

                    onBackPressed()
                }

                DialogInterface.BUTTON_NEGATIVE -> {}
            }
        }

        val dialog = AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle(getString(R.string.app_name))
            .setMessage(getString(R.string.text_cancel_parcel))
            .setPositiveButton(getString(R.string.text_yes), listener)
            .setNegativeButton(getString(R.string.text_no), listener)
            .setIcon(R.drawable.ic_app)
            .create()

        dialog.show()
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