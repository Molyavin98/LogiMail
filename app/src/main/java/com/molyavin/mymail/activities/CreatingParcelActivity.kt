package com.molyavin.mymail.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.molyavin.mymail.MenuActivity
import com.molyavin.mymail.R
import com.molyavin.mymail.call_delivery_gay.CallbackListener
import com.molyavin.mymail.call_delivery_gay.DialogAddressCall
import com.molyavin.mymail.creating_parcel.DialogInfo
import com.molyavin.mymail.database.DataBaseAuth
import com.molyavin.mymail.database.DataBaseParcel
import com.molyavin.mymail.databinding.ActivityCreatingParcelBinding

class CreatingParcelActivity : AppCompatActivity(), CallbackListener {

    private lateinit var binding: ActivityCreatingParcelBinding
    private lateinit var dBParcel:DataBaseParcel
    private var indicator: Int = 0


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatingParcelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        dBParcel = DataBaseParcel(binding)

        binding.btnSendAplication.setOnClickListener { dBParcel.saveDataBase() }

        binding.textSenderData.text = "Відправник\n${DataBaseAuth.fullName}\n${DataBaseAuth.phone}"

        /** Setting up the dropdown list adapters for the sender and recipient addresses using
         *the "street" array resource.
        */
        val street = resources.getStringArray(R.array.street)
        val adapter = ArrayAdapter(this,R.layout.dropdown_item,street)
        binding.dropDownSender.setAdapter(adapter)
        binding.dropDownRecipient.setAdapter(adapter)

        setOnClickListeners()
        radioGroupOnListener()

    }


    /**
     * Handlers for clicking buttons and text input fields.
     */
    private fun setOnClickListeners(){

        binding.addressesSender.setOnClickListener {
            indicator = 1
            showDialogAddress()

        }

        binding.addressesRecipient.setOnClickListener {
            indicator = 2
            showDialogAddress()
        }

        binding.textInfoRecipient.setOnClickListener {
            indicator = 3
            showDialogInfo()
        }

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Shows a dialog for selecting an address and updates the corresponding UI element
     */
    private fun showDialogAddress() {
        val dialogFragment = DialogAddressCall(this)
        dialogFragment.show(supportFragmentManager, "signature")
    }

    /**
     * Shows a dialog for selecting an address and updates the corresponding UI element
     */
    private fun  showDialogInfo(){
        val dialogFragment = DialogInfo(this)
        dialogFragment.show(supportFragmentManager, "signature")
    }


    /**
     * The function sets event listeners to radio groups to perform certain actions when they change.
     */
    private fun radioGroupOnListener(){

        binding.radioGroupSender.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {

                R.id.radioAddressSender -> {
                    binding.textFieldSender.visibility = View.GONE
                    binding.addressesSender.visibility = View.VISIBLE
                }
                R.id.radioDepSender -> {
                    binding.addressesSender.visibility = View.GONE
                    binding.textFieldSender.visibility = View.VISIBLE
                }
            }
        }

        binding.radioGroupRecipient.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {

                R.id.radioAddressRecipient -> {
                    binding.textFieldRecipient.visibility = View.GONE
                    binding.addressesRecipient.visibility = View.VISIBLE
                }
                R.id.radioDepRecipient -> {
                    binding.addressesRecipient.visibility = View.GONE
                    binding.textFieldRecipient.visibility = View.VISIBLE
                }
            }
        }
    }

    /**
     * Function get data received from dialog
     */
    override fun onDataReceived(data:String) {

        when (indicator) {
            1 -> {
                binding.addressesSender.text = data
                indicator = 0
            }
            2 -> {
                binding.addressesRecipient.text = data
                indicator = 0
            }
            3 -> {
                binding.textInfoRecipient.text = data
                indicator = 0
            }
        }
    }

}