package com.durand.vacunacionperu.ui.close

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bancom.cambix.model.error.ErrorMasterResponseModel
import com.durand.domain.model.advanceVaccination.AdvanceVaccinationMasterResponseModel
import com.durand.domain.usecases.GetAdvanceVaccinationUseCase
import com.durand.helper.error.Failure
import com.durand.vacunacionperu.util.ScreenState
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.Exception

class AdvanceVaccinationViewModel : ViewModel(), KoinComponent {

    private val getProfessionsUseCase: GetAdvanceVaccinationUseCase by inject()

    private var _state: MutableLiveData<ScreenState<AdvanceVaccinationState>> = MutableLiveData()

    val state: LiveData<ScreenState<AdvanceVaccinationState>>
        get() = _state

    fun getAdvanceVaccination() {
        try {
            _state.value = ScreenState.Loading
            viewModelScope.launch {
                getProfessionsUseCase.run("").either(::error, ::responseCategorie) }
        } catch (e: Exception) { }
    }

    private fun responseCategorie(list: AdvanceVaccinationMasterResponseModel) {
        try {
            _state.value = ScreenState.Render(AdvanceVaccinationState.ShowSuccess(list.data!!))
        } catch (e: Exception) { }
    }

    private fun error(failure: Failure) {
        try {
            when (failure) {
                is Failure.ServerError -> {

                    val error = failure.error as ErrorMasterResponseModel
                    _state.value =
                            ScreenState.Render(
                                    AdvanceVaccinationState.ShowError(
                                            error
                                    )
                            )
                    Log.d("josuecitoxd", "error:  $error")
                }
            }
        } catch (e: Exception) { }
    }
}