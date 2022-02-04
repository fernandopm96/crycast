package com.example.crycast.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GroupMessage (
    @PrimaryKey(autoGenerate = true)
    val messageGroupId: Int,
    val userId: Int,
    val groupId: Long,
    val text: String,
    val createDate: String
)


