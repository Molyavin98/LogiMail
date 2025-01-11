package com.ultimatekillaa.logimail.database

import com.google.firebase.database.*

abstract class DataBase {

    open var dataBase: DatabaseReference =
        FirebaseDatabase.getInstance().reference.child("user")

    abstract fun saveDataBase()

    abstract fun readDataBase()

}