package com.example.proyecto.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

// daba error así que le dimos permisos

val Context.currentConnectivityState: ConnectionStatus
    get() {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return getCurrentConnectivityStatus(connectivityManager)
    }

private fun getCurrentConnectivityStatus(
    connectivityManager: ConnectivityManager
): ConnectionStatus {

    val activeNetwork = connectivityManager.activeNetwork
    val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)

    val connected =
        capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true

    return if (connected)
        ConnectionStatus.Available
    else
        ConnectionStatus.Unavailable
}

fun Context.observeConnectivityAsFlow() = callbackFlow {

    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val callback = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            trySend(ConnectionStatus.Available)
        }

        override fun onLost(network: Network) {
            trySend(ConnectionStatus.Unavailable)
        }
    }

    connectivityManager.registerDefaultNetworkCallback(callback)

    trySend(currentConnectivityState)

    awaitClose {
        connectivityManager.unregisterNetworkCallback(callback)
    }
}
