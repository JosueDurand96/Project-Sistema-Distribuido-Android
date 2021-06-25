package com.durand.domain.repositories

import com.durand.domain.model.campaign.CampaignMasterResponseModel
import com.durand.domain.model.local_vaccination.LocalVaccinationMasterResponseModel
import com.durand.helper.base.ResultType
import com.durand.helper.error.Failure

interface CampaignRepository {
    suspend fun getCampaign(params: String): ResultType<Failure, CampaignMasterResponseModel>
}