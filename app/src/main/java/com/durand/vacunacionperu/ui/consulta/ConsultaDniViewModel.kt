package com.durand.vacunacionperu.ui.consulta

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bancom.cambix.model.error.ErrorMasterResponseModel
import com.durand.domain.model.campaign.CampaignMasterResponseModel
import com.durand.domain.model.consulta.ConsultaMasterResponseModel
import com.durand.domain.model.consulta.ConsultaResponseModel
import com.durand.domain.usecases.GetCampaignUseCase
import com.durand.domain.usecases.GetConsultaDniUseCase
import com.durand.helper.error.Failure
import com.durand.vacunacionperu.ui.campaign.CampaignState
import com.durand.vacunacionperu.util.ScreenState
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.Exception

class ConsultaDniViewModel : ViewModel(), KoinComponent {

    private val getConsultaDni: GetConsultaDniUseCase by inject()

    private var _state: MutableLiveData<ScreenState<ConsultaState>> = MutableLiveData()

    val state: LiveData<ScreenState<ConsultaState>>
        get() = _state

    fun getConsultaDni(dni: String) {
        try {
            _state.value = ScreenState.Loading
            viewModelScope.launch {
                getConsultaDni.run(dni).either(::error, ::responseCategorie)
            }
        } catch (e: Exception) {
        }
    }

    private fun responseCategorie(list: ConsultaMasterResponseModel) {
        try {
            _state.value = ScreenState.Render(ConsultaState.ShowSuccess(list))
        } catch (e: Exception) {
        }
    }

    private fun error(failure: Failure) {
        try {
            when (failure) {
                is Failure.ServerError -> {

                    val error = failure.error as ErrorMasterResponseModel
                    _state.value =
                        ScreenState.Render(
                            ConsultaState.ShowError(
                                error
                            )
                        )
                    Log.d("josuecitoxd", "error:  $error")
                }
            }
        } catch (e: Exception) {
        }
    }
}