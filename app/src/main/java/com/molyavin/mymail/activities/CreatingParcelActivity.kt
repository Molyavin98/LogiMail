package com.molyavin.mymail.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.molyavin.mymail.MenuActivity
import com.molyavin.mymail.R
import com.molyavin.mymail.utis.CallbackListener
import com.molyavin.mymail.activities.dialog.DialogAddressCall
import com.molyavin.mymail.activities.dialog.DialogInfo
import com.molyavin.mymail.database.DataBaseAuth
import com.molyavin.mymail.database.DataBaseParcel
import com.molyavin.mymail.databinding.ActivityCreatingParcelBinding
import com.molyavin.mymail.utis.NetworkChangeListener

class CreatingParcelActivity : AppCompatActivity(), CallbackListener {

    private lateinit var binding: ActivityCreatingParcelBinding
    private val networkChangeListener: NetworkChangeListener = NetworkChangeListener()
    private lateinit var dBParcel: DataBaseParcel

    private var indicator: Int = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatingParcelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dBParcel = DataBaseParcel(binding)

        binding.textSenderData.text = "Відправник\n${DataBaseAuth.fullName}\n${DataBaseAuth.phone}"

        /** Setting up the dropdown list adapters for the sender and recipient addresses using
         *the "street" array resource.
         */
        val street = resources.getStringArray(R.array.street)
        val adapter = ArrayAdapter(this, R.layout.dropdown_item, street)
        binding.dropDownSender.setAdapter(adapter)
        binding.dropDownRecipient.setAdapter(adapter)

        setOnClickListeners()
        radioGroupOnListener()

    }


    /**
     * Handlers for clicking buttons and text input fields.
     */
    private fun setOnClickListeners() {

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
            overridePendingTransition(R.anim.slideinback, R.anim.slideoutback)
        }

        binding.btnSendAplication.setOnClickListener { checkForCreateParcelErrors() }
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
    private fun showDialogInfo() {
        val dialogFragment = DialogInfo(this)
        dialogFragment.show(supportFragmentManager, "signature")
    }


    /**
     * The function sets event listeners to radio groups to perform certain actions when they change.
     */
    private fun radioGroupOnListener() {

        binding.radioGroupSender.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {

                R.id.radioAddressSender -> {
                    binding.textFieldSender.visibility = View.GONE
                    binding.addressesSender.visibility = View.VISIBLE
                    binding.textHintSender.visibility = View.VISIBLE
                    radioSender = false
                }
                R.id.radioDepSender -> {
                    binding.addressesSender.visibility = View.GONE
                    binding.textHintSender.visibility = View.GONE
                    binding.textFieldSender.visibility = View.VISIBLE
                    radioSender = true
                }
            }
        }

        binding.radioGroupRecipient.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {

                R.id.radioAddressRecipient -> {
                    binding.textFieldRecipient.visibility = View.GONE
                    binding.addressesRecipient.visibility = View.VISIBLE
                    binding.textHintRecipient.visibility = View.VISIBLE
                    radioRecipient = false
                }
                R.id.radioDepRecipient -> {
                    radioRecipient = true
                    binding.addressesRecipient.visibility = View.GONE
                    binding.textHintRecipient.visibility = View.GONE
                    binding.textFieldRecipient.visibility = View.VISIBLE
                }
            }
        }
    }

    /**
     * Function get data received from dialog
     */
    @SuppressLint("SetTextI18n")
    override fun onDataReceived(data: String) {

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

    private fun checkForCreateParcelErrors() {


        // Check if the recipient is selected and if its address is specified
        if (!radioSender) {
            if (binding.addressesSender.text == getString(R.string.text_address)) {
                Toast.makeText(this, getString(R.string.text_toast_enter_address_sender), Toast.LENGTH_SHORT).show()
                return
            }
        } else {
            if (binding.textFieldSender.editText?.text.toString() == getString(R.string.text_address_departament)) {
                Toast.makeText(this, getString(R.string.text_toast_enter_address_sender), Toast.LENGTH_SHORT).show()
                return
            }
        }
        // Check if the recipient data is specified
        if (!radioRecipient) {
            if (binding.addressesRecipient.text == getString(R.string.text_address)) {
                Toast.makeText(this, getString(R.string.text_toast_enter_address_recipient), Toast.LENGTH_SHORT).show()
                return
            }
        } else {
            if (binding.textFieldRecipient.editText?.text.toString() == getString(R.string.text_address_departament)) {
                Toast.makeText(this, getString(R.string.text_toast_enter_address_recipient), Toast.LENGTH_SHORT).show()
                return
            }
        }

        if (binding.textInfoRecipient.text == getString(R.string.text_recipient)) {
            Toast.makeText(this, getString(R.string.text_toast_data_recipient), Toast.LENGTH_SHORT).show()
            return
        }

        // Check if the type parcel is specified
        if (binding.editTextComment.editText?.text.toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.text_toast_enter_type_parcel), Toast.LENGTH_SHORT).show()
            return
        }

        // Save the data in the database and display a message about the creation of the package
        dBParcel.saveDataBase()
        Toast.makeText(this, getString(R.string.text_toast_parcel_create), Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MenuActivity::class.java))
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

    companion object {

        @JvmStatic
        var radioSender: Boolean = false

        @JvmStatic
        var radioRecipient: Boolean = false
    }

}