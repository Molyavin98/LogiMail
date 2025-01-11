package com.ultimatekillaa.logimail.creating_parcel

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ultimatekillaa.logimail.R
import com.ultimatekillaa.logimail.activities.AllParcelInfoActivity
import com.ultimatekillaa.logimail.utis.ParcelInfo

class Adapter() : RecyclerView.Adapter<Adapter.MyViewClass>() {

    private var parcelInfoList: List<ParcelInfo>? = null

    constructor(parcelInfoList: List<ParcelInfo>) : this() {
        this.parcelInfoList = parcelInfoList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewClass {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return MyViewClass(view)
    }

    override fun getItemCount(): Int {
        return parcelInfoList?.size ?: 0
    }


    override fun onBindViewHolder(holder: MyViewClass, position: Int) {
        val parcelInfo = parcelInfoList?.get(position)

        if (parcelInfo != null) {
            holder.ttn.text = parcelInfo.ttn

            holder.fullNameSender.text = parcelInfo.fullNameSender
            holder.numberSender.text = parcelInfo.numberSender
            holder.comment.text = parcelInfo.comment
        }

        holder.itemView.setOnClickListener(View.OnClickListener {
            val context = holder.itemView.context

            val intent = Intent(context, AllParcelInfoActivity::class.java)

            if (parcelInfo != null) {
                intent.putExtra("ttn", parcelInfo.ttn)
                intent.putExtra("full_name_sender", parcelInfo.fullNameSender)
                intent.putExtra("number_sender", parcelInfo.numberSender)
                intent.putExtra("address_sender",parcelInfo.addressSender)
                intent.putExtra("full_name_recipient", parcelInfo.fullNameRecipient)
                intent.putExtra("number_recipient", parcelInfo.numberRecipient)
                intent.putExtra("address_recipient", parcelInfo.addressRecipient)
                intent.putExtra("type_parcel",parcelInfo.comment)
                intent.putExtra("payment",parcelInfo.payment)
            }

            context.startActivity(intent)
        })


    }

    class MyViewClass(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var ttn: TextView = itemView.findViewById(R.id.ttnText)
        var fullNameSender: TextView = itemView.findViewById(R.id.fullNameSender)
        var numberSender: TextView = itemView.findViewById(R.id.numberSender)
        var comment: TextView = itemView.findViewById(R.id.textTypeParcel)
    }
}