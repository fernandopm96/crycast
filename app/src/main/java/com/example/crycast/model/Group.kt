package com.example.crycast.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Group(
    @PrimaryKey(autoGenerate = true)
    var groupId: Long,
    @ColumnInfo
    var name: String,
    @ColumnInfo
    var description: String
)
