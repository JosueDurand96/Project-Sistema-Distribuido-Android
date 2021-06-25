package com.durand.domain.repositories

import com.durand.domain.model.advanceVaccination.AdvanceVaccinationMasterResponseModel
import com.durand.domain.model.advanceVaccination.AdvanceVaccinationResponseModel
import com.durand.domain.model.campaign.CampaignMasterResponseModel
import com.durand.helper.base.ResultType
import com.durand.helper.error.Failure

interface AdvanceVaccinationRepository {
    suspend fun getAdvanceVaccination(params: String): ResultType<Failure, AdvanceVaccinationMasterResponseModel>
}