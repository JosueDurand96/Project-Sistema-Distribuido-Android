package com.durand.data.network

import com.durand.helper.error.Failure
import java.net.ConnectException

object ErrorUtil {

    fun handler(t: Throwable?): Failure {
        return when(t){
            is ConnectException -> Failure.NetworkConnection
            else -> Failure.DefaultError
        }
    }

}