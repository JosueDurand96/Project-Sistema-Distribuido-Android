package com.durand.data.implements

import com.bancom.cambix.entities.error.ErrorMasterResponseEntities
import com.durand.data.entities.campaign.CampaignMasterResponseEntities
import com.durand.data.entities.campaign.CampaignResponseEntities
import com.durand.data.entities.consulta.ConsultaMasterResponseEntities
import com.durand.data.entities.consulta.ConsultaResponseEntities
import com.durand.data.entities.vaccination.VaccinationResponseEntities
import com.durand.data.mapper.campaign.CampaignMapper
import com.durand.data.mapper.consulta.ConsultaMapper
import com.durand.data.mapper.error.ErrorMasterMapper
import com.durand.data.mapper.vaccination.VaccinationMapper
import com.durand.data.network.ErrorUtil
import com.durand.domain.model.campaign.CampaignMasterResponseModel
import com.durand.domain.model.consulta.ConsultaMasterResponseModel
import com.durand.domain.model.consulta.ConsultaResponseModel
import com.durand.domain.repositories.CampaignRepository
import com.durand.domain.repositories.ConsultaRepository
import com.durand.helper.base.ResultType
import com.durand.helper.error.Failure
import com.durand.networking.NetworkingManager
import com.durand.networking.model.NetworkingConfiguration
import com.durand.networking.service.ResultService
import com.durand.networking.util.NetworkingHttpVerb
import com.google.gson.Gson

class ConsultaRepositoryImpl : ConsultaRepository {
    val gson = Gson()

    override suspend fun getConsultaDni(params: String): ResultType<Failure, ConsultaMasterResponseModel> {

        val dni = params

        val query = "/api/dni/$dni"
        val networkConfiguration = NetworkingConfiguration.NetworkingConfigurationBuilder()
            .endpoint(query)
            .baseUrl("https://consulta.api-peru.com")
            .httpVerb(NetworkingHttpVerb.GET)
            .config()

        return try {
            val result: ResultService = NetworkingManager.with(networkConfiguration).connect()
            return if (result.isSuccessful) {
                val gson = Gson()

                val datoEntity: ConsultaMasterResponseEntities = gson.fromJson(gson.toJson(result.body), ConsultaMasterResponseEntities::class.java)
                ResultType.Success(ConsultaMapper.transform(datoEntity))

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