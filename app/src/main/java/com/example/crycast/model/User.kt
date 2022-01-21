package com.example.crycast.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class User(
    @PrimaryKey
    var id: Int = 0,
    @ColumnInfo
    var mail: String,
    @ColumnInfo
    var name: String,
    @ColumnInfo
    var pathImage: String?
)