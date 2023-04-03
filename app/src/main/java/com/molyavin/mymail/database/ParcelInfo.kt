package com.molyavin.mymail.database

class ParcelInfo {

    var fullNameSender: String? = null
    var addressSender: String? = null
    var addressRecipient: String? = null
    var fullNameRecipient: String? = null
    var comment: String? = null
    var payment:Boolean = false

    constructor(){}

    constructor(
        fullNameSender: String?,
        addressSender: String?,
        addressRecipient: String?,
        fullNameRecipient: String?,
        comment: String?,
        payment: Boolean
    ) {
        this.fullNameSender = fullNameSender
        this.addressSender = addressSender
        this.addressRecipient = addressRecipient
        this.fullNameRecipient = fullNameRecipient
        this.comment = comment
        this.payment = payment
    }
}