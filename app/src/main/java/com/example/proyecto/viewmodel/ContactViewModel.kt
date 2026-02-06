package com.example.proyecto.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.data.local.ContactEntity
import com.example.proyecto.data.repositorio.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactRepository: ContactRepository
    // private val networkHelper: NetworkHelper
) : ViewModel() {

    //Estado mutable con la lista de contactos
    private val _contactos = MutableStateFlow<List<ContactEntity>>(emptyList())

    //Estado observable no modificable
    val contactos: StateFlow<List<ContactEntity>> = _contactos

    val _cargando: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val cargando: StateFlow<Boolean> = _cargando

    // Estado observable para la UI
    // private val _isConnected = MutableStateFlow(false)
    // val isConnected: StateFlow<Boolean> = _isConnected


    //Inicializamos el ViewModel
    init {
        // 1) Escuchar contactos (obtenerTodosLosContactos)
        viewModelScope.launch {
            contactRepository.obtenerTodosLosContactos().collect { // collectAsState en pantalla
                _contactos.value = it
                _cargando.value = false
            }
        }

        //2) Escuchar conexión (NetworkHelper)
       //  viewModelScope.launch {
                //   networkHelper.isConnectedFlow().collect {
                // _isConnected.value = it
           // }
        // }

    }

    //Carga de contacto por API
/*
fun loadContactAPI() {
        viewModelScope.launch {
            // Si no hay internet, no llamamos a la API
            if (!_isConnected.value) {
                Log.d("ContactoViewModel", "No hay conexión a internet")
            } else {

                try {
                    //Obtener el contacto de la API
                    val newContacto = contactRepository.getNewContact()
                    //Vemos su log
                    Log.d("ContactoViewModel", newContacto.toString())
                    //Lo insertamos en la BBDD
                    insertarContact(newContacto)
                } catch (e: Exception) {
                    // Si algo falla, el estado sigue siendo el anterior
                    // o podrías ponerlo a null de nuevo
                    Log.e("ContactoViewModel", "Error al cargar el contacto: $e")
                }
            }
        }
    }
*/

    //Insertamos contacto en la BBDD
    fun insertarContact(contacto: ContactEntity) {
        viewModelScope.launch {
            contactRepository.insertarContacto(contacto)
        }
    }

    //Eliminar contacto de BBDD
    fun eliminarContact(contacto: ContactEntity) {
        viewModelScope.launch {
            contactRepository.eliminarContacto(contacto)
        }
    }
}