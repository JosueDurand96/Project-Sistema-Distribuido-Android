package com.durand.vacunacionperu.ui.local_vaccination

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.durand.domain.model.local_vaccination.LocalVaccinationResponseModel
import com.durand.helper.base.BaseFragment
import com.durand.vacunacionperu.R
import com.durand.vacunacionperu.util.ScreenState
import kotlinx.android.synthetic.main.fragment_local_vaccination.*

class LocalVaccinationFragment : BaseFragment() {

    private lateinit var localVaccinationViewModel: LocalVaccinationViewModel
    private lateinit var vaccinationAdapter: LocalVaccinationAdapter
    private lateinit var localVaccinationRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        localVaccinationViewModel = ViewModelProvider(this).get(LocalVaccinationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_local_vaccination, container, false)
        localVaccinationRecyclerView = root.findViewById(R.id.localVaccinationRecyclerView)
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        localVaccinationViewModel.state.observe(::getLifecycle, ::getVaccination)
        initGetVaccination()
    }

    private fun initGetVaccination(){
         vaccinationLocalProgressBar.visibility = View.VISIBLE
        localVaccinationViewModel.getVaccination()
    }
    private fun getVaccination(screenState: ScreenState<LocalVaccinationState>) {
        when (screenState) {
            is ScreenState.Render -> registerProcessRenderState(screenState.renderState)
        }
    }

    private fun registerProcessRenderState(renderState: LocalVaccinationState) {
        when (renderState) {
            is LocalVaccinationState.ShowSuccess -> {
                Log.d("josuecitoxd", "ShowSuccess: ${renderState.reg[0].s_nombre}")
                vaccinationList(renderState.reg)
                vaccinationLocalProgressBar.visibility = View.GONE
            }
            is LocalVaccinationState.ShowError -> {
                Log.d("josuecitoxd", "error: " + renderState.reg.message)
            }
        }
    }

    private fun vaccinationList(list: List<LocalVaccinationResponseModel>) {
        vaccinationAdapter = LocalVaccinationAdapter(context as Activity, list)
        localVaccinationRecyclerView.setHasFixedSize(true)
        localVaccinationRecyclerView.adapter = vaccinationAdapter
        localVaccinationRecyclerView.layoutManager = LinearLayoutManager(context)
    }
}