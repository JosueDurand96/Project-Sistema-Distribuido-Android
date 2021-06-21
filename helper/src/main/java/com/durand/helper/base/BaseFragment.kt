package com.durand.helper.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.durand.helper.BuildConfig
import com.durand.networking.NetworkingManager
import com.durand.networking.model.Networking
import com.durand.networking.model.NetworkingBaseConfiguration
import com.durand.networking.util.NetworkingType

abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //ThemeManager.setTheme(this)
        initNetworkingConfiguration()

    }


    private fun initNetworkingConfiguration() {

        val networkingConfiguration = NetworkingBaseConfiguration.BancomNetworkingBaseConfigurationBuilder()
            .baseUrl(BuildConfig.BASE_URL)
            .timeout(100)
            .mutual(false)
            .type(NetworkingType.COROUTINES)
            .build()

        val bcpNetworking = Networking.BCPNetworkingBuilder()
            .networkingBaseConfiguration(networkingConfiguration)
            .build()

        NetworkingManager.init(bcpNetworking)

    }



}

 fun initNetworkingConfiguration(baseUrl:String) {

    val networkingConfiguration = NetworkingBaseConfiguration.BancomNetworkingBaseConfigurationBuilder()
        .baseUrl(baseUrl)
        .timeout(10)
        .mutual(false)
        .type(NetworkingType.COROUTINES)
        .build()

    val bcpNetworking = Networking.BCPNetworkingBuilder()
        .networkingBaseConfiguration(networkingConfiguration)
        .build()

    NetworkingManager.init(bcpNetworking)

}
