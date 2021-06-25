package com.durand.vacunacionperu.ui.vaccination.add

import com.bancom.cambix.model.error.ErrorMasterResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel


sealed class AddVaccinationState {
    class ShowError(val reg: ErrorMasterResponseModel) : AddVaccinationState()
    class ShowSuccess(val reg: VaccinationResponseModel) : AddVaccinationState()
}