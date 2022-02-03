package com.example.crycast.model

import androidx.room.Entity

@Entity(primaryKeys = ["groupId", "userId"])
data class GroupsUsers(
    var groupId: Long,
    var userId: Int
)