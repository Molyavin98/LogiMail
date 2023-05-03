package com.molyavin.mymail.activities.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputLayout
import com.molyavin.mymail.R
import com.molyavin.mymail.utis.CallbackListener
import com.molyavin.mymail.check_error.CheckErrorUser


class DialogAddressCall(private val callbackListener: CallbackListener) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false

        ViewGroup.LayoutParams.MATCH_PARENT
        return inflater.inflate(R.layout.fragment_dialog_address, container, false)
    }


    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnDone: Button = view.findViewById(R.id.btnDone)
        val btnBack: ImageButton = view.findViewById(R.id.btnBack)
        val fieldCity: TextInputLayout = view.findViewById(R.id.fieldCity)
        val fieldStreet: TextInputLayout = view.findViewById(R.id.fieldStreet)
        val fieldNumBuilder: TextInputLayout = view.findViewById(R.id.fieldNumBuilder)

        val check = CheckErrorUser(view.context)

        btnDone.setOnClickListener {

            check.checkIsEmpty(fieldCity)
            check.checkIsEmpty(fieldStreet)
            check.checkIsEmpty(fieldNumBuilder)

            if (check.checkIsEmpty(fieldCity)
                && check.checkIsEmpty(fieldStreet)
                && check.checkIsEmpty(fieldNumBuilder)
            ) {

                callbackListener.onDataReceived(
                    "м.${fieldCity.editText?.text.toString()}\n" +
                            "вул.${fieldStreet.editText?.text.toString()}, " +
                            fieldNumBuilder.editText?.text.toString())
                dismiss()
            }
        }

        btnBack.setOnClickListener {
            callbackListener.onDataReceived(getString(R.string.text_address))
            dismiss()
        }
    }
}