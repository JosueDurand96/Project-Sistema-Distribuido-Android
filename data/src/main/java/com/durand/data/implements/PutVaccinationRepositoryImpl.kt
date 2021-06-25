package com.durand.data.implements

import com.bancom.cambix.entities.error.ErrorMasterResponseEntities
import com.durand.data.entities.vaccination.PostVaccinationRequestEntities
import com.durand.data.entities.vaccination.PutVaccinationRequestEntities
import com.durand.data.entities.vaccination.VaccinationResponseEntities
import com.durand.data.mapper.error.ErrorMasterMapper
import com.durand.data.mapper.vaccination.PostVaccinationRequestMapper
import com.durand.data.mapper.vaccination.PutVaccinationRequestMapper
import com.durand.data.mapper.vaccination.VaccinationMapper
import com.durand.data.network.ErrorUtil
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.domain.repositories.PostVaccinationRepository
import com.durand.domain.repositories.PutVaccinationRepository
import com.durand.domain.request.PutVaccinationRequest
import com.durand.domain.request.VaccinationRequest
import com.durand.helper.base.ResultType
import com.durand.helper.error.Failure
import com.durand.networking.NetworkingManager
import com.durand.networking.model.NetworkingConfiguration
import com.durand.networking.service.ResultService
import com.durand.networking.util.NetworkingHttpVerb
import com.google.gson.Gson


class PutVaccinationRepositoryImpl : PutVaccinationRepository {
    val gson = Gson()
    override suspend fun getVaccination(product: PutVaccinationRequest): ResultType<Failure, VaccinationResponseModel> {
        val body: PutVaccinationRequestEntities = PutVaccinationRequestMapper.transformRequest(product)
        val query = "/vacunapp/api/vacuna"
        val networkConfiguration = NetworkingConfiguration.NetworkingConfigurationBuilder()
            .endpoint(query)
            .body(body)
            .httpVerb(NetworkingHttpVerb.PUT)
            .config()

        return try {
            val result: ResultService = NetworkingManager.with(networkConfiguration).connect()
            return if (result.isSuccessful) {
                val datoEntity: VaccinationResponseEntities =
                    gson.fromJson(gson.toJson(result.body), VaccinationResponseEntities::class.java)
                ResultType.Success(VaccinationMapper.transformListCard2(datoEntity!!))
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