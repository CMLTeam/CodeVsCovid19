package com.cmlteam.covidapp.validator.dto

import java.io.Serializable

data class BookSlotRequest(val userId: Int, val slotId: Int) : Serializable