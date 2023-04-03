package com.molyavin.mymail.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.molyavin.mymail.MenuActivity
import com.molyavin.mymail.call_delivery_gay.CallbackListener
import com.molyavin.mymail.call_delivery_gay.DialogAddressCall
import com.molyavin.mymail.check_error.CheckErrorUser
import com.molyavin.mymail.database.DataBaseAuth
import com.molyavin.mymail.databinding.ActivityCallDeliveryGayBinding

class CallDeliveryGayActivity : AppCompatActivity(), CallbackListener {

    private lateinit var binding: ActivityCallDeliveryGayBinding
    private lateinit var check: CheckErrorUser
    private var address:String = ""

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCallDeliveryGayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        check = CheckErrorUser(this)

        binding.textSenderData.text = "Відправник\n${DataBaseAuth.fullName}\n${DataBaseAuth.phone}"


        onClickListener()

    }


    private fun onClickListener() {

        binding.addressesPeople.setOnClickListener {
            showDialog()
        }


        binding.btnSendAplication.setOnClickListener {
            sendAplication()
        }

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }


    private fun sendAplication() {

        if (!check.checkIsEmpty(binding.editTypeParcel) || binding.addressesPeople.text.equals("Адреса")) {
            Toast.makeText(this, "Вкажіть адресу!", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Заявка прийнята!", Toast.LENGTH_LONG).show()
        }
    }

    private fun showDialog() {
        val dialogFragment = DialogAddressCall(this)
        dialogFragment.show(supportFragmentManager, "signature")
    }

    override fun onDataReceived(data: String) {
        address = data
        binding.addressesPeople.text = data
    }

}