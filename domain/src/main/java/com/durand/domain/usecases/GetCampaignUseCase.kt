package com.durand.domain.usecases

import com.durand.domain.model.campaign.CampaignMasterResponseModel
import com.durand.domain.repositories.CampaignRepository
import com.durand.helper.base.ResultType
import com.durand.helper.base.UseCase
import com.durand.helper.error.Failure

class GetCampaignUseCase(private val lineRepository: CampaignRepository) :
        UseCase<CampaignMasterResponseModel, String>() {

    override suspend fun run(params: String): ResultType<Failure, CampaignMasterResponseModel> {
        return lineRepository.getCampaign(params)
    }
}