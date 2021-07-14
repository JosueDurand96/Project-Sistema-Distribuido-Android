package com.durand.data.implements

import com.bancom.cambix.entities.error.ErrorMasterResponseEntities
import com.durand.data.entities.campaign.CampaignResponseEntities
import com.durand.data.entities.vaccination.VaccinationResponseEntities
import com.durand.data.mapper.campaign.CampaignMapper
import com.durand.data.mapper.error.ErrorMasterMapper
import com.durand.data.mapper.vaccination.VaccinationMapper
import com.durand.data.network.ErrorUtil
import com.durand.domain.model.campaign.CampaignResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.domain.repositories.DeleteCampaignRepository
import com.durand.domain.repositories.DeleteVaccinationRepository
import com.durand.helper.base.ResultType
import com.durand.helper.error.Failure
import com.durand.networking.NetworkingManager
import com.durand.networking.model.NetworkingConfiguration
import com.durand.networking.service.ResultService
import com.durand.networking.util.NetworkingHttpVerb
import com.google.gson.Gson

class DeleteCampaignRepositoryImpl : DeleteCampaignRepository {

    val gson = Gson()

    override suspend fun getCampaign(id: String): ResultType<Failure, CampaignResponseModel> {
        val idCampaign = id
        val query = "/vacunapp/api/campana/$idCampaign"
        val networkConfiguration = NetworkingConfiguration.NetworkingConfigurationBuilder()
            .endpoint(query)
            .httpVerb(NetworkingHttpVerb.DELETE)
            .config()

        return try {
            val result: ResultService = NetworkingManager.with(networkConfiguration).connect()
            return if (result.isSuccessful) {

                val dataEntity: CampaignResponseEntities =
                    gson.fromJson(gson.toJson(result.body), CampaignResponseEntities::class.java)
                ResultType.Success(CampaignMapper.transformListCard2(dataEntity))
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