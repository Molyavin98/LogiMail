package com.molyavin.mymail.database

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.molyavin.mymail.databinding.ActivityCreatingParcelBinding

class DataBaseParcel(val binding: ActivityCreatingParcelBinding) : DataBase() {

    override var dataBase: DatabaseReference =
    FirebaseDatabase.getInstance().reference.child("parcel")

    override fun saveDataBase() {
        dataBase.child("test").setValue("hello")
    }

    override fun readDataBase() {
        TODO("Not yet implemented")
    }


}