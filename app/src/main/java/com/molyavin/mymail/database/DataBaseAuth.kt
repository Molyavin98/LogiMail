package com.molyavin.mymail.database

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class DataBaseAuth(private val idUser:String) : DataBase() {


    override fun saveDataBase() {}

    override fun readDataBase() {

        val eListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val user: Users? = dataSnapshot.getValue(Users::class.java)

                fullName = user?.fullName.toString()
                phone = user?.phone.toString()
            }

            override fun onCancelled(error: DatabaseError) {}

        }
        dataBase.child(idUser).addValueEventListener(eListener)
    }

    companion object{

        @JvmStatic
        var fullName:String = ""
        @JvmStatic
        var phone:String = ""
    }
}