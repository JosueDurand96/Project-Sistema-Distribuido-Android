package com.durand.networking.interactor.coroutine

import com.durand.networking.interactor.coroutine.NetworkingCoroutineInteractor
import com.durand.networking.service.ResultService
import com.durand.networking.model.NetworkingConfiguration
import com.durand.networking.util.NetworkingHttpVerb

/**
 * Middle class between BCPNetworkingManager and BCPNetworkingCoroutineInteractor
 *
 * @param bcpNetworkingConfiguration module configuration
 */
class NetworkingCoroutineBuilder(
    private val bcpNetworkingConfiguration: NetworkingConfiguration
) {

    /**
     * Depending to the httpVerb (BCPNetworkingConfiguration), notify BCPNetworkingCoroutineInteractor
     */
    suspend fun connect(): ResultService {

        return when (bcpNetworkingConfiguration.httpVerb) {
            NetworkingHttpVerb.GET -> NetworkingCoroutineInteractor.GET.execute(bcpNetworkingConfiguration)
            NetworkingHttpVerb.POST -> NetworkingCoroutineInteractor.POST.execute(bcpNetworkingConfiguration)
            NetworkingHttpVerb.PUT -> NetworkingCoroutineInteractor.PUT.execute(bcpNetworkingConfiguration)
            NetworkingHttpVerb.PATCH -> NetworkingCoroutineInteractor.PATCH.execute(bcpNetworkingConfiguration)
            NetworkingHttpVerb.DELETE -> NetworkingCoroutineInteractor.DELETE.execute(bcpNetworkingConfiguration)
            else -> NetworkingCoroutineInteractor.GET.execute(bcpNetworkingConfiguration)
        }
    }
}