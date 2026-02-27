package com.example.proyecto.data.repository

import com.example.proyecto.data.local.ContactDao
import com.example.proyecto.data.local.ContactEntity
import com.example.proyecto.data.remote.datasource.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactRepository @Inject constructor(

    // api
    private val apiService: ApiService,

    // local room
    private val contactDao: ContactDao

) {

    suspend fun getNewContact(): ContactEntity {

        val result = apiService.getContact().results.first()
        return result.toEntity()
    }

    suspend fun insertarContacto(contactEntity: ContactEntity) {
        contactDao.insertarContacto(contactEntity)
    }

    suspend fun eliminarContacto(contactEntity: ContactEntity) {
        contactDao.eliminarContacto(contactEntity)
    }

    fun obtenerTodosLosContactos(): Flow<List<ContactEntity>> {
        return contactDao.obtenerTodosLosContactos()
    }

    // para actualizar como el de borrar pero actualizando
    suspend fun actualizarContacto(contactEntity: ContactEntity)
    {
        contactDao.actualizarContacto(contactEntity)
    }
}
