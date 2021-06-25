package com.durand.data.implements

import com.bancom.cambix.entities.error.ErrorMasterResponseEntities
import com.durand.data.entities.campaign.CampaignMasterResponseEntities
import com.durand.data.entities.campaign.CampaignResponseEntities
import com.durand.data.entities.local_vaccination.LocalVaccinationMasterResponseEntities
import com.durand.data.entities.local_vaccination.LocalVaccinationResponseEntities
import com.durand.data.mapper.campaign.CampaignMapper
import com.durand.data.mapper.error.ErrorMasterMapper
import com.durand.data.mapper.local_vaccination.LocalVaccinationMapper
import com.durand.data.network.ErrorUtil
import com.durand.domain.model.campaign.CampaignMasterResponseModel
import com.durand.domain.model.local_vaccination.LocalVaccinationMasterResponseModel
import com.durand.domain.repositories.CampaignRepository
import com.durand.domain.repositories.LocalVaccinationRepository
import com.durand.helper.base.ResultType
import com.durand.helper.error.Failure
import com.durand.networking.NetworkingManager
import com.durand.networking.model.NetworkingConfiguration
import com.durand.networking.service.ResultService
import com.durand.networking.util.NetworkingHttpVerb
import com.google.gson.Gson


class CampaignRepositoryImpl : CampaignRepository {

    val gson = Gson()

    override suspend fun getCampaign(params: String): ResultType<Failure, CampaignMasterResponseModel> {
        val query = "/vacunapp/api/campana"
        val networkConfiguration = NetworkingConfiguration.NetworkingConfigurationBuilder()
                .endpoint(query)
                .httpVerb(NetworkingHttpVerb.GET)
                .config()

        return try {
            val result: ResultService = NetworkingManager.with(networkConfiguration).connect()
            return if (result.isSuccessful) {
                val gson = Gson()
                val refVar = gson.fromJson(gson.toJson(result.body), Array<CampaignResponseEntities>::class.java)
                val professionsMapper = CampaignMapper.transform(CampaignMasterResponseEntities(refVar))
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