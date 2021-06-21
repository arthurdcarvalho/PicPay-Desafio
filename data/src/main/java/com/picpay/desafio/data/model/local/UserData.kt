package com.picpay.desafio.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "T_USER")
data class UserData(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val username: String,
    val img: String
)