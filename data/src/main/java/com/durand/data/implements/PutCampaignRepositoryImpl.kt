package com.durand.data.implements

import com.bancom.cambix.entities.error.ErrorMasterResponseEntities
import com.durand.data.entities.campaign.CampaignResponseEntities
import com.durand.data.entities.campaign.PutCampaignRequestEntities
import com.durand.data.entities.vaccination.PutVaccinationRequestEntities
import com.durand.data.entities.vaccination.VaccinationResponseEntities
import com.durand.data.mapper.campaign.CampaignMapper
import com.durand.data.mapper.campaign.PutCampaignRequestMapper
import com.durand.data.mapper.error.ErrorMasterMapper
import com.durand.data.mapper.vaccination.PutVaccinationRequestMapper
import com.durand.data.mapper.vaccination.VaccinationMapper
import com.durand.data.network.ErrorUtil
import com.durand.domain.model.campaign.CampaignResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.domain.repositories.PutCampaignRepository
import com.durand.domain.repositories.PutVaccinationRepository
import com.durand.domain.request.PutCampaignRequest
import com.durand.domain.request.PutVaccinationRequest
import com.durand.helper.base.ResultType
import com.durand.helper.error.Failure
import com.durand.networking.NetworkingManager
import com.durand.networking.model.NetworkingConfiguration
import com.durand.networking.service.ResultService
import com.durand.networking.util.NetworkingHttpVerb
import com.google.gson.Gson


class PutCampaignRepositoryImpl : PutCampaignRepository {

    val gson = Gson()
    override suspend fun getCampaign(product: PutCampaignRequest): ResultType<Failure, CampaignResponseModel> {
        val body: PutCampaignRequestEntities = PutCampaignRequestMapper.transformRequest(product)
        val query = "/vacunapp/api/campana"
        val networkConfiguration = NetworkingConfiguration.NetworkingConfigurationBuilder()
            .endpoint(query)
            .body(body)
            .httpVerb(NetworkingHttpVerb.PUT)
            .config()

        return try {
            val result: ResultService = NetworkingManager.with(networkConfiguration).connect()
            return if (result.isSuccessful) {
                val datoEntity: CampaignResponseEntities = gson.fromJson(gson.toJson(result.body), CampaignResponseEntities::class.java)
                ResultType.Success(CampaignMapper.transformListCard2(datoEntity))
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