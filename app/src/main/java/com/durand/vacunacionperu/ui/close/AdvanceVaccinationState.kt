package com.durand.vacunacionperu.ui.close

import com.bancom.cambix.model.error.ErrorMasterResponseModel
import com.durand.domain.model.advanceVaccination.AdvanceVaccinationResponseModel

sealed class AdvanceVaccinationState {
    class ShowError(val reg: ErrorMasterResponseModel) : AdvanceVaccinationState()
    class ShowSuccess(val reg: List<AdvanceVaccinationResponseModel>) : AdvanceVaccinationState()
}