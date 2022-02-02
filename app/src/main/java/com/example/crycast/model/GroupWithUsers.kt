package com.example.crycast.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class GroupWithUsers (
    @Embedded val group: Group,
    @Relation(
        parentColumn = "groupId",
        entityColumn = "userId",
        associateBy = Junction(GroupsUsers::class)
    )
    val users: List<User>
)