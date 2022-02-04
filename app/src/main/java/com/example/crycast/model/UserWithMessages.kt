package com.example.crycast.model

import androidx.room.Embedded
import androidx.room.Relation


data class MessageGroupWithUser (
    @Embedded val groupMessage: GroupMessage,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val user: User
    )