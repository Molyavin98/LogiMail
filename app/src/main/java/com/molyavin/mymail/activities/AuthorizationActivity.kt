package com.molyavin.mymail.activities

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import com.molyavin.mymail.MenuActivity
import com.molyavin.mymail.R
import com.molyavin.mymail.check_error.CheckErrorUser

import com.molyavin.mymail.databinding.ActivityAuthorizationBinding
import com.molyavin.mymail.utis.NetworkChangeListener

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityAuthorizationBinding

private lateinit var mAuth: FirebaseAuth
private lateinit var progressDialog: ProgressDialog

@SuppressLint("StaticFieldLeak")
private lateinit var check: CheckErrorUser
private lateinit var mSettings: SharedPreferences
private val networkChangeListener: NetworkChangeListener = NetworkChangeListener()

const val USER_DATA_AUTH: String = "user_auth"
const val USER_ID: String = "user_id"
const val EMAIL_AUTH: String = "user_email"
const val PASSWORD_AUTH: String = "user_pass"

class AuthorizationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthorizationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mSettings = getSharedPreferences(USER_DATA_AUTH, Context.MODE_PRIVATE)
        check = CheckErrorUser(this)
        progressDialog = ProgressDialog(this)
        mAuth = FirebaseAuth.getInstance()

        onClickListener()
    }

    private fun onClickListener() {

        binding.btnRegistration.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
            overridePendingTransition(R.anim.slidein, R.anim.slideout)

        }

        binding.btnAuthorization.setOnClickListener {
            performAunt()
        }
    }

    private fun performAunt() {

        val email: String = binding.fieldEmail.editText?.text.toString()
        val passwordOne: String = binding.textPassword.editText?.text.toString()

        check.checkEmail(binding.fieldEmail)
        check.checkPasswordAuth(binding.textPassword)

        if (check.checkEmail(binding.fieldEmail) && check.checkPasswordAuth(binding.textPassword)) {
            progressDialog.setMessage(getString(R.string.text_please_waite))
            progressDialog.setTitle(getString(R.string.text_auth))
            progressDialog.setCanceledOnTouchOutside(false)
            progressDialog.show()

            mAuth.signInWithEmailAndPassword(email, passwordOne)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        progressDialog.dismiss()
                        sendUserToNextActivity()
                        saveDataAuthLocal(email, passwordOne, mAuth.uid.toString())
                        Toast.makeText(
                            this,
                            getString(R.string.text_auth_finish),
                            Toast.LENGTH_LONG
                        ).show()
                        binding.textPassword.error = null
                    } else {
                        progressDialog.dismiss()
                        if (!check.checkPasswordAuth(binding.textPassword)) {
                            binding.textPassword.error = getString(R.string.text_no_correct_pass)
                        }
                        binding.fieldEmail.error = getString(R.string.not_user)
                    }
                }
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun saveDataAuthLocal(email: String, pass: String, userId: String) {

        val editor: SharedPreferences.Editor = mSettings.edit()

        if (binding.saveDataAuth.isChecked) {
            editor.putString(EMAIL_AUTH, email)
            editor.putString(PASSWORD_AUTH, pass)
            editor.putString(USER_ID, userId)
            editor.apply()
        } else {
            editor.putString(EMAIL_AUTH, null)
            editor.putString(PASSWORD_AUTH, null)
            editor.putString(USER_ID, userId)
            editor.apply()
        }
    }

    private fun sendUserToNextActivity() {
        val intent = Intent(this, MenuActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
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