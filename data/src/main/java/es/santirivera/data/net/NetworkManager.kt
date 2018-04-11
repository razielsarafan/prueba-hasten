package es.santirivera.data.net

import es.santirivera.data.exceptions.NetworkUnavailableException


interface NetworkManager {

    fun checkConnectivity()

    @Throws(NetworkUnavailableException::class)
    fun checkWifi(): Boolean
}
