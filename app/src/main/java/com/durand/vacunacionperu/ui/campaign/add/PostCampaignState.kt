package com.durand.vacunacionperu.ui.campaign.add

import com.bancom.cambix.model.error.ErrorMasterResponseModel
import com.durand.domain.model.campaign.CampaignResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel

sealed class PostCampaignState {
    class ShowError(val reg: ErrorMasterResponseModel) : PostCampaignState()
    class ShowSuccess(val reg: CampaignResponseModel) : PostCampaignState()
}