package com.durand.vacunacionperu.di

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Build
import com.durand.data.implements.LocalVaccinationRepositoryImpl
import com.durand.data.implements.VaccinationRepositoryImpl
import com.durand.domain.repositories.LocalVaccinationRepository
import com.durand.domain.repositories.VaccinationRepository
import com.durand.domain.usecases.GetLocalVaccinationUseCase
import com.durand.domain.usecases.GetVaccinationUseCase
import com.durand.vacunacionperu.ui.local_vaccination.LocalVaccinationViewModel
import com.durand.vacunacionperu.ui.vaccination.VaccinationViewModel
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val applicationModule = module {

    //vaccination
    single { GetVaccinationUseCase(get()) }
    single<VaccinationRepository> { VaccinationRepositoryImpl() }
    viewModel { VaccinationViewModel() }

    //local vaccination
    single { GetLocalVaccinationUseCase(get()) }
    single<LocalVaccinationRepository> { LocalVaccinationRepositoryImpl() }
    viewModel { LocalVaccinationViewModel() }

}