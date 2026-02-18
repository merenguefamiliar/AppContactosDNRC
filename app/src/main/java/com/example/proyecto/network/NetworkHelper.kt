package com.example.proyecto.network

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NetworkHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    // dice si ta conectado
    fun isConnectedFlow(): Flow<Boolean> {
        return context.observeConnectivityAsFlow()
            .map { it == ConnectionStatus.Available }
    }

    fun isConnectedNow(): Boolean {
        return context.currentConnectivityState == ConnectionStatus.Available
    }
}
