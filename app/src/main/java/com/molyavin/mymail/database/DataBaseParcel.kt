package com.molyavin.mymail.database

import com.google.firebase.database.*
import com.molyavin.mymail.activities.dialog.DialogInfo
import com.molyavin.mymail.databinding.ActivityCreatingParcelBinding
import com.molyavin.mymail.utis.ParcelInfo
import java.util.*

class DataBaseParcel(val binding: ActivityCreatingParcelBinding) : DataBase() {



    override var dataBase: DatabaseReference =
        FirebaseDatabase.getInstance().reference.child("parcel")

    override fun saveDataBase() {

        var addressSender: String? = null
        var addressRecipient: String? = null


        if (binding.radioAddressSender.isChecked || binding.radioAddressRecipient.isChecked) {
            addressSender = binding.addressesSender.text.toString()
            addressRecipient = binding.addressesRecipient.text.toString()
        } else if (binding.radioDepSender.isChecked || binding.radioDepRecipient.isChecked) {
            addressSender = binding.textFieldSender.editText?.text.toString()
            addressRecipient = binding.textFieldRecipient.editText?.text.toString()
        }

        val ttn = Random().nextInt(1000000000).toString().padStart(10, '0')

        val parcelInfo = ParcelInfo(
            ttn,
            DataBaseAuth.fullName,
            DataBaseAuth.phone,
            addressSender,
            DialogInfo.recipientFullName,
            DialogInfo.recipientNumber,
            addressRecipient,
            binding.editTextComment.editText?.text.toString(),
            binding.switchPay.isChecked
        )
        dataBase.child(DataBaseAuth.phone).child(ttn).setValue(parcelInfo)
        dataBase.child(DialogInfo.recipientNumber.toString()).child(ttn).setValue(parcelInfo)
    }

    override fun readDataBase() {}





}