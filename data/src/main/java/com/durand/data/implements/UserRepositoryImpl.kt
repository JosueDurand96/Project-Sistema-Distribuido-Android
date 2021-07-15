package com.durand.data.implements

import com.bancom.cambix.entities.error.ErrorMasterResponseEntities
import com.durand.data.entities.user.UserMasterResponseEntities
import com.durand.data.entities.user.UserResponseEntities
import com.durand.data.entities.vaccination.VaccinationMasterResponseEntities
import com.durand.data.entities.vaccination.VaccinationResponseEntities
import com.durand.data.mapper.error.ErrorMasterMapper
import com.durand.data.mapper.user.UserMapper
import com.durand.data.mapper.vaccination.VaccinationMapper
import com.durand.data.network.ErrorUtil
import com.durand.domain.model.user.UserMasterResponseModel
import com.durand.domain.model.vaccination.VaccinationMasterResponseModel
import com.durand.domain.repositories.UserRepository
import com.durand.helper.base.ResultType
import com.durand.helper.error.Failure
import com.durand.networking.NetworkingManager
import com.durand.networking.model.NetworkingConfiguration
import com.durand.networking.service.ResultService
import com.durand.networking.util.NetworkingHttpVerb
import com.google.gson.Gson

class UserRepositoryImpl: UserRepository {

    val gson = Gson()

    override suspend fun getUser(params: String): ResultType<Failure, UserMasterResponseModel> {
        val query = "/vacunapp/api/persona"
        val networkConfiguration = NetworkingConfiguration.NetworkingConfigurationBuilder()
            .endpoint(query)
            .httpVerb(NetworkingHttpVerb.GET)
            .config()

        return try {
            val result: ResultService = NetworkingManager.with(networkConfiguration).connect()
            return if (result.isSuccessful) {
                val gson = Gson()
                val refVar = gson.fromJson(gson.toJson(result.body),
                    Array<UserResponseEntities>::class.java)
                val professionsMapper = UserMapper.transform(UserMasterResponseEntities(refVar))
                ResultType.Success(professionsMapper)

            } else {
                val error: ErrorMasterResponseEntities = gson.fromJson(
                    gson.toJson(result.error),
                    ErrorMasterResponseEntities::class.java
                )
                ResultType.Failure(
                    Failure.ServerError(
                        ErrorMasterMapper().mapToEntity(error)
                    )
                )
            }
        } catch (e: Exception) {
            ResultType.Failure(ErrorUtil.handler(e))
        }
    }
}