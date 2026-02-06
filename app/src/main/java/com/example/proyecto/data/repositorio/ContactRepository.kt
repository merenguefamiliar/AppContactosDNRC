package com.example.proyecto.data.repositorio

import com.example.proyecto.data.local.ContactDao
import com.example.proyecto.data.local.ContactEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactRepository @Inject constructor(
    // Inyectamos la fuente de datos remota (API)
    // El repositorio NO crea la API, solo la usa
    private val contactDao: ContactDao
)  {

    // Esta función obtiene un nuevo contacto desde la API
    // Es suspend porque realiza operaciones asíncronas (peticiones de red)

    /*
    suspend fun getNewContact(): ContactEntity {

        // Llamamos a la API para obtener los datos necesarios
        //la API devuelve arrays

        val result = dataSource.getContact().results.first()

        // Convertimos los datos de la API (modelo remoto)
        // en un ContactEntity (modelo local de la app)
        // El repositorio se encarga de esta transformación
        val contactEntity = ContactEntity(
            name = result.name.first,
            lastName = result.name.last,
            city = result.location.city,
            state = result.location.state,
            thumbnail = result.picture.thumbnail,
            phone = result.phone,
            email = result.email
        )
        return contactEntity
    }
    */

    //Función para simular API - Si tenemos error en la API
    /*suspend fun getNewContact(): ContactEntity {
        // Simulamos un retardo de red de 1 segundo
        kotlinx.coroutines.delay(1000)

        // Devolvemos un contacto manual para que la app no falle
        return ContactoEntity(
            title = "Sr.",
            name = "Usuario",
            lastName = "De Prueba (Servidor Offline)",
            phone = "600 000 000",
            email = "prueba@example.com",
            city = "Madrid",
            country = "España",
            thumbnail = "https://randomuser.me/api/portraits/thumb/men/1.jpg"
        )
    }*/



    //Función para insertar un contacto en la BBDD
    suspend fun insertarContacto(contactEntity: ContactEntity){
        contactDao.insertarContacto(contactEntity)
    }

    //Función para eliminar un contacto de la BBDD
    suspend fun eliminarContacto(contactEntity: ContactEntity) {
        contactDao.eliminarContacto(contactEntity)
    }

    //Función para obtener todos los contactos de la BBDD
    fun obtenerTodosLosContactos(): Flow<List<ContactEntity>> {
        return contactDao.obtenerTodosLosContactos()
    }
}