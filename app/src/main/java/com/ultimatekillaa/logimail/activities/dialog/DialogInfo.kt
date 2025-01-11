package com.ultimatekillaa.logimail.activities.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputLayout
import com.ultimatekillaa.logimail.R
import com.ultimatekillaa.logimail.utis.CallbackListener
import com.ultimatekillaa.logimail.check_error.CheckErrorUser

class DialogInfo(private val callbackListener: CallbackListener) : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        isCancelable = false
        ViewGroup.LayoutParams.MATCH_PARENT

        return inflater.inflate(R.layout.fragment_dialog_info_user, container, false)
    }


    override fun getTheme(): Int {
        return R.style.DialogTheme
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnDone: Button = view.findViewById(R.id.btnDone)
        val fieldNumber: TextInputLayout = view.findViewById(R.id.textNumPhone)
        val fieldFullName: TextInputLayout = view.findViewById(R.id.fieldFullName)
        val btnBack: ImageButton = view.findViewById(R.id.btnBack)

        val check = CheckErrorUser(view.context)

        btnDone.setOnClickListener {

            check.checkNumber(fieldNumber)
            check.checkFullName(fieldFullName)

            if (check.checkNumber(fieldNumber) && check.checkFullName(fieldFullName)) {

                callbackListener.onDataReceived(
                    "${getString(R.string.text_recipient)}\n${fieldFullName.editText?.text.toString()}\n" +
                            "+380${fieldNumber.editText?.text.toString()}"
                )

                recipientNumber = "+380${fieldNumber.editText?.text.toString()}"
                recipientFullName = fieldFullName.editText?.text.toString()
                dismiss()
            }
        }

        btnBack.setOnClickListener {
            callbackListener.onDataReceived(getString(R.string.text_recipient))
            dismiss()
        }

    }


    companion object{

        @JvmStatic
        var recipientNumber: String? = null

        @JvmStatic
        var recipientFullName:String? = null
    }
}