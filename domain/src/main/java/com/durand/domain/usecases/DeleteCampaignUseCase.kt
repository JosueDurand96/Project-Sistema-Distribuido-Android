package com.durand.domain.usecases

import com.durand.domain.model.campaign.CampaignResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.domain.repositories.DeleteCampaignRepository
import com.durand.domain.repositories.DeleteVaccinationRepository
import com.durand.helper.base.ResultType
import com.durand.helper.base.UseCase
import com.durand.helper.error.Failure


class DeleteCampaignUseCase(private val lineRepository: DeleteCampaignRepository) :
    UseCase<CampaignResponseModel, String>() {

    override suspend fun run(params: String): ResultType<Failure, CampaignResponseModel> {
        return lineRepository.getCampaign(params)
    }
}