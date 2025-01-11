package com.ultimatekillaa.logimail.database

import android.util.Log
import com.ultimatekillaa.logimail.databinding.ActivityRegistrationBinding
import com.ultimatekillaa.logimail.utis.Users

class DataBaseRegistration(private val binding: ActivityRegistrationBinding, private val idUser:String) : DataBase() {


    override fun saveDataBase() {
        val users = Users(
            binding.fullNameField.editText?.text.toString(),
            binding.fieldEmail.editText?.text.toString(),
            "+380${binding.textNumPhone.editText?.text.toString()}"
        )

        dataBase.child(idUser).setValue(users)

        Log.e("test",idUser)
    }

    override fun readDataBase() {
        TODO("Not yet implemented")
    }
}