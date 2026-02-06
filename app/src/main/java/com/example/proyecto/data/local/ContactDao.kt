package com.example.proyecto.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

//Interfaz de acceso a datos (Data Access Object)
@Dao
interface ContactDao {

    //Insertar un contacto en la base de datos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarContacto(contacto: ContactEntity)

    //Consulta que obtiene los contactos de la tabla contactos ordenados por id de más nuevo a más antiguo
    @Query("SELECT * FROM contactos ORDER BY id DESC")
    fun obtenerTodosLosContactos(): Flow<List<ContactEntity>>

    //Eliminar Contacto
    @Delete
    suspend fun eliminarContacto(contacto: ContactEntity)
}