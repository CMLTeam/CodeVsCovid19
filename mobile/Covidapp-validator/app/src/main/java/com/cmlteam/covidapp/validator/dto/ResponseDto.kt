package com.cmlteam.covidapp.validator.dto

data class Customer(
    val id: Int, // customer id
    val phoneNumber: String, // phoneNumber,
    val documentId: String, //some sort of ID for government, acquired via BankID (potentially)
    val score: Int, // from 0 to 1000
    val status: String, // enum: [normal, required_doctor_visit, covid19_positive]
    val address: String, // just to print
    val pictureUrl: String, // to put it in <img> tag
    val closeCommunicationWith: List<Int>? // ids of customers user communicates with frequently
)

data class Target(
    val id: Int, // slots could be fetched by this id as targetId
    val name: String,
    val distance: Int, // distance in meters from you,
    val maxPeopleCapacity: Int, // how many people curr. target could initially handle
    val address: String,
    val latitude: Float,
    val longitude: Float,
    val workHours: String,
    val pictureUrl: String?, // to put it in <img> tag
    val slots: List<Slot>
)

data class Slot(
    val id: Int,
    val targetId: Int,
    val description: String, //string representation
    val startDate: String, // ISO8601 datetime , to sort by,
    val endDate: String, // ISO8601, end date to maybe sort by
    val freeCapacity: Int // how many people may join slot
)

data class SlotValidationResult(
    val slotsValidated: List<Slot>  //slots reserved by user
)

