package com.durand.domain.repositories

import com.durand.domain.model.campaign.CampaignResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.domain.request.CampaignRequest
import com.durand.domain.request.VaccinationRequest
import com.durand.helper.base.ResultType
import com.durand.helper.error.Failure


interface PostCampaignRepository {
    suspend fun getCampaign(params: CampaignRequest): ResultType<Failure, CampaignResponseModel>
}