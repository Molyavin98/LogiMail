package com.ultimatekillaa.logimail.check_error

import android.content.Context
import com.google.android.material.textfield.TextInputLayout
import com.ultimatekillaa.logimail.R

val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")

class CheckErrorUser (val context: Context) {

    fun checkNumber(editText: TextInputLayout): Boolean {

        val number: String = editText.editText?.text.toString()

        if (number.isEmpty()) {
            editText.error = context.getString(R.string.field_is_empty)
        } else if (!checkCodeNumber(editText)) {
            editText.error = context.getString(R.string.not_number)
        } else if (number.length > 9 || number.length < 9) {
            editText.error = context.getString(R.string.number_no_correct)
        } else {
            editText.error = null
            return true
        }
        return false
    }

    fun checkEmail(emailField: TextInputLayout): Boolean {

        val email: String = emailField.editText?.text.toString()

        if (email.isEmpty()) {
            emailField.error = context.getString(R.string.field_is_empty)
        } else if (!email.matches(emailPattern)) {
            emailField.error = context.getString(R.string.input_correct_email)
        } else {
            emailField.error = null
            return true
        }
        return false
    }

    fun checkFullName(fullNameField: TextInputLayout): Boolean {

        val fullName: String = fullNameField.editText?.text.toString()

        if (fullName.isEmpty()) {
            fullNameField.error = context.getString(R.string.field_is_empty)
        } else {
            fullNameField.error = null
            return true
        }
        return false
    }

    fun checkPassword(password_1: TextInputLayout, password_2: TextInputLayout): Boolean {

        val passwordOne: String = password_1.editText?.text.toString()
        val passwordTwo: String = password_2.editText?.text.toString()

        if (passwordOne.isEmpty() || passwordOne.length < 6) {
            password_2.error = context.getString(R.string.field_pass_is_empty_or_l6)
        } else if (passwordOne != passwordTwo) {
            password_2.error = context.getString(R.string.passwords_not_similar)
        } else {
            password_2.error = null
            return true
        }
        return false
    }

    fun checkPasswordAuth(pass: TextInputLayout): Boolean {

        val password: String = pass.editText?.text.toString()

        if (password.isEmpty()) {
            pass.error = context.getString(R.string.field_pass_is_empty)
        } else {
            pass.error = null
            return true
        }
        return false
    }


    fun checkIsEmpty(field:TextInputLayout) : Boolean{

        val location:String = field.editText?.text.toString()

        if(location.isEmpty()) {
            field.error = context.getString(R.string.field_is_empty)
        }else{
            field.error = null
            return true
        }
        return false
    }

    // A function that checks the code of the mobile operator
    private fun checkCodeNumber(editText: TextInputLayout): Boolean {

        val number: String = editText.editText?.text.toString()

        if (number.startsWith("50")) {
            return true
        } else if (number.startsWith("63")) {
            return true
        } else if (number.startsWith("66")) {
            return true
        } else if (number.startsWith("67")) {
            return true
        } else if (number.startsWith("68")) {
            return true
        } else if (number.startsWith("91")) {
            return true
        } else if (number.startsWith("92")) {
            return true
        } else if (number.startsWith("93")) {
            return true
        } else if (number.startsWith("94")) {
            return true
        } else if (number.startsWith("95")) {
            return true
        } else if (number.startsWith("96")) {
            return true
        } else if (number.startsWith("97")) {
            return true
        } else if (number.startsWith("98")) {
            return true
        } else if (number.startsWith("99")) {
            return true
        }
        return false
    }




}