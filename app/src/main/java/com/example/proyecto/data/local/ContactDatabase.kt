package com.example.proyecto.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

//Creamos la clase ContactDatabase

@Database(
    entities = [ContactEntity::class],
    version = 1,
    exportSchema = false
)

abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}