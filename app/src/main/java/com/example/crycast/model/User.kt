package com.example.crycast.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo
    var mail: String,
    @ColumnInfo
    var password: String,
    @ColumnInfo
    var name: String,
    @ColumnInfo
    var pathImage: String?
)