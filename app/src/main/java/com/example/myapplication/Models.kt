package com.example.myapplication

data class Call(
        val contactName: Contact,
        val outGoingCall: Boolean,
        val time: String,
        val callDuration: String,
        val callDetails: String,
        val cost : String
)

data class Sms(
        val contactName: Contact,
        val time: String,
        val destail: String
)

data class DataUsage(
        val dataQuantity: String,
        val dataDetails: String,
        val cost : String
)

data class Contact(
        val name: String,
        val avatarUrl: String
)

