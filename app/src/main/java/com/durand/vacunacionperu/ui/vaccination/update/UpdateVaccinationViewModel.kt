package com.durand.vacunacionperu.ui.vaccination.update

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bancom.cambix.model.error.ErrorMasterResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.domain.request.PutVaccinationRequest
import com.durand.domain.request.VaccinationRequest
import com.durand.domain.usecases.PostVaccinationUseCase
import com.durand.domain.usecases.PutVaccinationUseCase
import com.durand.helper.error.Failure
import com.durand.vacunacionperu.ui.vaccination.add.AddVaccinationState
import com.durand.vacunacionperu.util.ScreenState
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.Exception

class UpdateVaccinationViewModel : ViewModel(), KoinComponent {

    private val putVaccinationUseCase: PutVaccinationUseCase by inject()

    private var _state: MutableLiveData<ScreenState<AddVaccinationState>> = MutableLiveData()

    val state: LiveData<ScreenState<AddVaccinationState>>
        get() = _state

    fun postVaccination(vaccinationRequest: PutVaccinationRequest) {
        try {
            _state.value = ScreenState.Loading
            viewModelScope.launch {
                putVaccinationUseCase.run(vaccinationRequest).either(::error, ::responseCategorie) }
        } catch (e: Exception) { }
    }

    private fun responseCategorie(list: VaccinationResponseModel) {
        try {
            _state.value = ScreenState.Render(AddVaccinationState.ShowSuccess(list))
        } catch (e: Exception) { }
    }

    private fun error(failure: Failure) {
        try {
            when (failure) {
                is Failure.ServerError -> {

                    val error = failure.error as ErrorMasterResponseModel
                    _state.value =
                        ScreenState.Render(
                            AddVaccinationState.ShowError(
                                error
                            )
                        )
                    Log.d("josuecitoxd", "error:  $error")
                }
            }
        } catch (e: Exception) { }
    }
}