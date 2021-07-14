package com.durand.domain.repositories

import com.durand.domain.model.campaign.CampaignResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.domain.request.PutCampaignRequest
import com.durand.domain.request.PutVaccinationRequest
import com.durand.helper.base.ResultType
import com.durand.helper.error.Failure

interface PutCampaignRepository {
    suspend fun getCampaign(params: PutCampaignRequest): ResultType<Failure, CampaignResponseModel>
}