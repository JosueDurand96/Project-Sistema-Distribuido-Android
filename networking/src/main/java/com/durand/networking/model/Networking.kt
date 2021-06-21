package com.durand.networking.model

class Networking private constructor(
    /* certificate pwd*/
    var pwd: CharArray?,
    /* certificate in string */
    var certificate: String?,
    /* BCPNetworkingBaseConfiguration init */
    var networkingBaseConfiguration: NetworkingBaseConfiguration?
) {

    data class BCPNetworkingBuilder(
        var pwd: CharArray? = null,
        var certificate: String? = null,
        var networkingBaseConfiguration: NetworkingBaseConfiguration? = null
    ) {

        fun pwd(pwd: CharArray) = apply { this.pwd = pwd }
        fun certificate(certificate: String) = apply { this.certificate = certificate }
        fun networkingBaseConfiguration(networkingBaseConfiguration1: NetworkingBaseConfiguration) =
            apply { this.networkingBaseConfiguration = networkingBaseConfiguration1 }

        fun build() = Networking(
            pwd,
            certificate,
            networkingBaseConfiguration
        )
    }
}