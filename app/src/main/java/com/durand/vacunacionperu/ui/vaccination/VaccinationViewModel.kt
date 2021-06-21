package com.durand.vacunacionperu.ui.vaccination

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bancom.cambix.model.error.ErrorMasterResponseModel
import com.durand.domain.model.vaccination.VaccinationMasterResponseModel
import com.durand.domain.usecases.GetVaccinationUseCase
import com.durand.helper.error.Failure
import com.durand.vacunacionperu.util.ScreenState
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.Exception

class VaccinationViewModel : ViewModel(), KoinComponent {

    private val getProfessionsUseCase: GetVaccinationUseCase by inject()

    private var _state: MutableLiveData<ScreenState<VaccinationState>> = MutableLiveData()

    val state: LiveData<ScreenState<VaccinationState>>
        get() = _state

    fun getVaccination() {
        try {
            _state.value = ScreenState.Loading
            viewModelScope.launch {
                getProfessionsUseCase.run("").either(::error, ::responseCategorie)
            }
        } catch (e: Exception) {
        }
    }

    private fun responseCategorie(list: VaccinationMasterResponseModel) {
        try {
            _state.value = ScreenState.Render(VaccinationState.ShowSuccess(list.data!!))
        } catch (e: Exception) { }
    }

    private fun error(failure: Failure) {
        try {
            when (failure) {
                is Failure.ServerError -> {

                    val error = failure.error as ErrorMasterResponseModel
                    _state.value =
                        ScreenState.Render(
                            VaccinationState.ShowError(
                                error
                            )
                        )
                    Log.d("josuecitoxd", "error:  $error")
                }
            }
        } catch (e: Exception) { }
    }
}