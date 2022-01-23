package com.example.crycast.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PrivateMessage (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val idUser: Int,
    val text: String,
    val idDestinationUser: Int
)