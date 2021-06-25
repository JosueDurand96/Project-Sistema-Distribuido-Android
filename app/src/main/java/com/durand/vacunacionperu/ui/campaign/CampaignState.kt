package com.durand.vacunacionperu.ui.campaign

import com.bancom.cambix.model.error.ErrorMasterResponseModel
import com.durand.domain.model.campaign.CampaignResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel

sealed class CampaignState {
    class ShowError(val reg: ErrorMasterResponseModel) : CampaignState()
    class ShowSuccess(val reg: List<CampaignResponseModel>) : CampaignState()
}