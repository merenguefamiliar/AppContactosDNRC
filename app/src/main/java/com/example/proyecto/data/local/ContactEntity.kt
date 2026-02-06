package com.example.proyecto.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contactos")
data class ContactEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val lastName: String,
    val city: String,
    val state: String,
    val thumbnail: String,
    val phone: String,
    val email: String
)