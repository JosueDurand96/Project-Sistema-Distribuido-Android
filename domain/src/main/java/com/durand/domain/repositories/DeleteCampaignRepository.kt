package com.durand.domain.repositories

import com.durand.domain.model.campaign.CampaignResponseModel
import com.durand.helper.base.ResultType
import com.durand.helper.error.Failure

interface DeleteCampaignRepository {
    suspend fun getCampaign(params: String): ResultType<Failure, CampaignResponseModel>
}