package com.durand.helper.base

import com.durand.helper.error.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): ResultType<Failure, Type>

    suspend operator fun invoke(params: Params, onResult: (ResultType<Failure, Type>) -> Unit = {}) {
        val result = withContext(Dispatchers.IO) {
            run(params)
        }

        onResult(result)
    }

}

abstract class UseCase2<out Type, in Params, in Params2> where Type : Any {

    abstract suspend fun run(params: Params,params2: Params2): ResultType<Failure, Type>

    suspend operator fun invoke(params: Params,params2: Params2, onResult: (ResultType<Failure, Type>) -> Unit = {}) {
        val result = withContext(Dispatchers.IO) {
            run(params,params2)
        }

        onResult(result)
    }

}