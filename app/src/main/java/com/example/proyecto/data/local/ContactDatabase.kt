package com.example.proyecto.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

//Creamos la clase ContactDatabase

@Database(
    entities = [ContactEntity::class],
    version = 4, // cuando agregue una nueva fila o algo en la base de datos cambiar esto o peta, aunque se borra tdo
    exportSchema = false
)

abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}