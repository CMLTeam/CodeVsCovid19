package com.cmlteam.covidapp.dto

data class Customer(
    val id: Int,
    val phoneNumber: String,
    val documentId: String,
    val name: String,
    val illnessRate: Int,
    val status: String,
    val address: String,
    val pictureUrl: String,
    val closeCommunicationWith: List<Int>,
    val bookedSlots: List<Int>
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
    val targetId: Int,
    val description: String, //string representation
    val startDate: String, // ISO8601 datetime , to sort by,
    val endDate: String, // ISO8601, end date to maybe sort by
    val freeCapacity: Int // how many people may join slot
)

data class SlotValidationResult(
    val slotsValidated: List<Slot>  //slots reserved by user
)

