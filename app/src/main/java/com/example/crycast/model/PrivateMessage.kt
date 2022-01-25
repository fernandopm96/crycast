package com.example.crycast.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PrivateMessage (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val crycastUserId: Int,
    val text: String,
    val crycastDestinationUserId: Int,
    val createDate: String
)