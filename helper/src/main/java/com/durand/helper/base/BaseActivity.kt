package com.durand.helper.base

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.durand.helper.BuildConfig
import com.durand.networking.NetworkingManager
import com.durand.networking.model.Networking
import com.durand.networking.model.NetworkingBaseConfiguration
import com.durand.networking.util.NetworkingType

abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // ThemeManager.setTheme(this)
        initNetworkingConfiguration()
    }

    open fun showProgress() {
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    open fun hideProgress() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
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
            .pwd("h1l2s4p5e7A8B7P4E5V".toCharArray())
            //.certificate(InputStreamUtil.inputStreamToString(CertificateUtil.certificate(this)))
            .build()

        NetworkingManager.init(bcpNetworking)

    }

}