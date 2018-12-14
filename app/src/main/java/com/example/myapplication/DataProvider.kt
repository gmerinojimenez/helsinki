package com.example.myapplication

import java.util.*


val contacts = listOf(
        Contact("Mamá", ""),
        Contact("Rajoy", ""),
        Contact("Tato Loco", ""),
        Contact("Zaryn Denzel", ""),
        Contact("655 123 123", "")
)

val callType = listOf(true, false)

val times = listOf("23:34", "12:14", "8:36", "13:22", "13:55")

val duration = listOf("4m 33s", "2m 13s", "1m 33s", "8m 53s")

val cost = listOf("4,00€", "1,40€", "0,50€", "2,12€", "0,34€", "6,20€")

val dataUsage = listOf("234MB", "13MB", "345MB", "123MB", "12MB")

fun randInt(min: Int, max: Int): Int {
    return Random().nextInt(max - min + 1) + min
}

fun provideCallUsage(): List<Call> {
    val calls = mutableListOf<Call>()
    for (i in 0..15) {
        calls.add(generateRandomCall())
    }
    return calls
}

fun provideDataUsage(): List<DataUsage> {
    val dataUsage = mutableListOf<DataUsage>()
    for (i in 0..15) {
        dataUsage.add(generateRandomData())
    }
    return dataUsage
}

fun provideSms(): List<Sms> {
    val sms = mutableListOf<Sms>()
    for (i in 0..15) {
        sms.add(generateRandomSms())
    }
    return sms
}

fun generateRandomCall() =
        Call(
                contacts[randInt(0, 4)],
                callType[randInt(0, 1)],
                times[randInt(0, 3)],
                duration[randInt(0, 3)],
                "Línea móvil",
                cost[randInt(0, 5)]
        )

fun generateRandomData() =
        DataUsage(
                dataUsage[randInt(0, 4)],
                "Datos móviles",
                cost[randInt(0, 5)]
        )

fun generateRandomSms() =
        Sms(
                contacts[randInt(0, 4)],
                times[randInt(0, 3)],
                cost[randInt(0, 5)]
        )

