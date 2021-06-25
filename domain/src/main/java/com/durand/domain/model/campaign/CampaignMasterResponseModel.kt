package com.durand.domain.model.campaign

import com.durand.domain.model.local_vaccination.LocalVaccinationResponseModel

data class CampaignMasterResponseModel (
        var data: List<CampaignResponseModel>? = null
        )