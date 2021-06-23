package com.durand.vacunacionperu.ui.local_vaccination

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bancom.cambix.model.error.ErrorMasterResponseModel
import com.durand.domain.model.local_vaccination.LocalVaccinationMasterResponseModel
import com.durand.domain.usecases.GetLocalVaccinationUseCase

import com.durand.domain.usecases.GetVaccinationUseCase
import com.durand.helper.error.Failure
import com.durand.vacunacionperu.ui.local_vaccination.LocalVaccinationState
import com.durand.vacunacionperu.util.ScreenState
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.Exception


class LocalVaccinationViewModel : ViewModel(), KoinComponent {

    private val getProfessionsUseCase: GetLocalVaccinationUseCase by inject()

    private var _state: MutableLiveData<ScreenState<LocalVaccinationState>> = MutableLiveData()

    val state: LiveData<ScreenState<LocalVaccinationState>>
        get() = _state

    fun getVaccination() {
        try {
            _state.value = ScreenState.Loading
            viewModelScope.launch {
                getProfessionsUseCase.run("").either(::error, ::responseCategorie) }
        } catch (e: Exception) { }
    }

    private fun responseCategorie(list: LocalVaccinationMasterResponseModel) {
        try {
            _state.value = ScreenState.Render(LocalVaccinationState.ShowSuccess(list.data!!))
        } catch (e: Exception) { }
    }

    private fun error(failure: Failure) {
        try {
            when (failure) {
                is Failure.ServerError -> {

                    val error = failure.error as ErrorMasterResponseModel
                    _state.value =
                            ScreenState.Render(
                                    LocalVaccinationState.ShowError(
                                            error
                                    )
                            )
                    Log.d("josuecitoxd", "error:  $error")
                }
            }
        } catch (e: Exception) { }
    }
}