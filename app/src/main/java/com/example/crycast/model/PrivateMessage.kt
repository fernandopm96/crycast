package com.example.crycast.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PrivateMessage(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val crycastUserId: String,
    val text: String,
    val crycastDestinationUserId: String,
    val createDate: String
)