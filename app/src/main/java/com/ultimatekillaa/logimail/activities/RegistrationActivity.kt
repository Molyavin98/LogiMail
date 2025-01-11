package com.ultimatekillaa.logimail.activities

import android.app.ProgressDialog
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.ultimatekillaa.logimail.R
import com.ultimatekillaa.logimail.check_error.CheckErrorUser
import com.ultimatekillaa.logimail.database.DataBaseRegistration
import com.ultimatekillaa.logimail.databinding.ActivityRegistrationBinding
import com.ultimatekillaa.logimail.utis.NetworkChangeListener

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var check:CheckErrorUser
    private lateinit var progressDialog:ProgressDialog
    private lateinit var mAuth: FirebaseAuth
    private lateinit var db:DataBaseRegistration
    private val networkChangeListener: NetworkChangeListener = NetworkChangeListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        check = CheckErrorUser(this)
        progressDialog = ProgressDialog(this)
        mAuth = FirebaseAuth.getInstance()
        Log.e("RegAct", mAuth.uid.toString())

        onClickListener()
    }

    private fun onClickListener(){
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, AuthorizationActivity::class.java))
            overridePendingTransition(R.anim.slideinback,R.anim.slideoutback)
        }

        binding.btnRegistration.setOnClickListener {
            performAuth()
        }
    }

    private fun performAuth() {

        val email: String = binding.fieldEmail.editText?.text.toString()
        val passwordOne: String = binding.textFieldOne.editText?.text.toString()

        check.checkNumber(binding.textNumPhone)
        check.checkEmail(binding.fieldEmail)
        check.checkFullName(binding.fullNameField)
        check.checkPassword(binding.textFieldOne, binding.textFieldTwo)

        if (check.checkNumber(binding.textNumPhone)
            && check.checkFullName(binding.fullNameField)
            && check.checkEmail(binding.fieldEmail)
            && check.checkPassword(binding.textFieldOne, binding.textFieldTwo)
        ) {
        progressDialog.setMessage(getString(R.string.text_please_waite_reg))
            progressDialog.setTitle(getString(R.string.text_registration))
            progressDialog.setCanceledOnTouchOutside(false)
            progressDialog.show()

            mAuth.createUserWithEmailAndPassword(email, passwordOne)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        progressDialog.dismiss()
                        sendUserToNextActivity()
                        db = DataBaseRegistration(
                            binding,
                            mAuth.uid.toString()
                        )
                        db.saveDataBase()
                        Log.e("RegActmet", mAuth.uid.toString())

                        Toast.makeText(this, getString(R.string.text_reg_finish), Toast.LENGTH_LONG)
                            .show()
                    } else {
                        progressDialog.dismiss()
                        binding.fieldEmail.error = getString(R.string.text_user_is_reg)
                        Toast.makeText(
                            this,
                            getString(R.string.text_user_is_reg),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun sendUserToNextActivity() {
        val intent = Intent(this, AuthorizationActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        overridePendingTransition(R.anim.slideinback,R.anim.slideoutback)
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

    override fun onBackPressed() {}
}