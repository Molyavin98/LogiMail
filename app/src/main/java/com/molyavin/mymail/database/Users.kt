package com.molyavin.mymail.database

class Users {

    var fullName: String? = null
    var email: String? = null
    var phone: String? = null

    constructor() {}

    constructor(name: String?, email: String?, phone: String?) {
        this.fullName = name
        this.email = email
        this.phone = phone
    }
}