package com.molyavin.mymail.utis

class ParcelInfo {

    var ttn: String? = null
    var fullNameSender: String? = null
    var numberSender: String? = null
    var addressSender: String? = null
    var fullNameRecipient: String? = null
    var numberRecipient: String? = null
    var addressRecipient: String? = null
    var comment: String? = null
    var payment: Boolean = false

    constructor() {}
    constructor(
        ttn: String?,
        fullNameSender: String?,
        numberSender: String?,
        addressSender: String?,
        fullNameRecipient: String?,
        numberRecipient: String?,
        addressRecipient: String?,
        comment: String?,
        payment: Boolean
    ) {
        this.ttn = ttn
        this.fullNameSender = fullNameSender
        this.numberSender = numberSender
        this.addressSender = addressSender
        this.fullNameRecipient = fullNameRecipient
        this.numberRecipient = numberRecipient
        this.addressRecipient = addressRecipient
        this.comment = comment
        this.payment = payment
    }


}