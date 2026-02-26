package com.example.proyecto.viewmodel

import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.data.local.ContactEntity
import com.example.proyecto.data.repository.ContactRepository
import com.example.proyecto.network.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactRepository: ContactRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {
    private val _contactos = MutableStateFlow<List<ContactEntity>>(emptyList())
    val contactos: StateFlow<List<ContactEntity>> = _contactos

    val _cargando: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val cargando: StateFlow<Boolean> = _cargando

    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> = _isConnected

    init {
        viewModelScope.launch {
            contactRepository.obtenerTodosLosContactos().collect {
                _contactos.value = it
                _cargando.value = false
            }
        }
        viewModelScope.launch {
            networkHelper.isConnectedFlow().collect {
                _isConnected.value = it
            }
        }
    }

    fun loadContactAPI() {
        viewModelScope.launch {
            if (!_isConnected.value) {
                Log.d("ContactoViewModel", "No hay conexión a internet")

            } else {

                try {
                    val newContacto = contactRepository.getNewContact()
                    insertarContact(newContacto)
                } catch (e: Exception) {
                    Log.e("ContactViewModel", "Error al cargar contacto: $e")
                }
            }
        }
    }

    fun insertarContact(contacto: ContactEntity) {
        viewModelScope.launch {
            contactRepository.insertarContacto(contacto)
        }
    }

    fun eliminarContact(contacto: ContactEntity) {
        viewModelScope.launch {
            contactRepository.eliminarContacto(contacto)
        }
    }


    fun actualizarContact(contacto: ContactEntity) {
        viewModelScope.launch {
            contactRepository.actualizarContacto(contacto)
        }
    }
}
