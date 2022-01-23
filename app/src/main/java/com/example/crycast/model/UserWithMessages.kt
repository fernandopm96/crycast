package com.example.crycast.model

import androidx.room.Embedded
import androidx.room.Relation


data class UserWithMessages (
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "idUser"
    )
    val messages: List<PrivateMessage>?
    )