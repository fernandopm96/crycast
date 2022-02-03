package com.example.crycast.model

import androidx.room.Embedded
import androidx.room.Relation


data class UserWithMessages (
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "crycastUserId"
    )
    val messages: List<PrivateMessage>?
    )