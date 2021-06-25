package com.durand.vacunacionperu.ui.advance_vaccination

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.durand.domain.model.advanceVaccination.AdvanceVaccinationResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.helper.base.BaseFragment
import com.durand.vacunacionperu.R
import com.durand.vacunacionperu.ui.vaccination.VaccinationAdapter
import com.durand.vacunacionperu.ui.vaccination.VaccinationState
import com.durand.vacunacionperu.ui.vaccination.VaccinationViewModel
import com.durand.vacunacionperu.util.ScreenState
import kotlinx.android.synthetic.main.fragment_advance_vaccination.*
import kotlinx.android.synthetic.main.fragment_vaccination.*
import kotlinx.android.synthetic.main.fragment_vaccination.vaccinationProgressBar

class AdvanceVaccinationFragment : BaseFragment() {

    private lateinit var advanceVaccinationViewModel: AdvanceVaccinationViewModel
    private lateinit var advanceVaccinationAdapter: AdvanceVaccinationAdapter
    private lateinit var advanceVaccinationRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        advanceVaccinationViewModel = ViewModelProvider(this).get(AdvanceVaccinationViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_advance_vaccination, container, false)
        advanceVaccinationRecyclerView = root.findViewById(R.id.advanceVaccinationRecyclerView)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGetVaccination()
        advanceVaccinationViewModel.state.observe(::getLifecycle, ::getVaccination)
    }

    private fun initGetVaccination(){
        advanceVaccinationProgressBar.visibility = View.VISIBLE
        advanceVaccinationViewModel.getAdvanceVaccination()
    }


    private fun getVaccination(screenState: ScreenState<AdvanceVaccinationState>) {
        when (screenState) {
            is ScreenState.Render -> registerProcessRenderState(screenState.renderState)
        }
    }

    private fun registerProcessRenderState(renderState: AdvanceVaccinationState) {
        when (renderState) {
            is AdvanceVaccinationState.ShowSuccess -> {
                advanceVaccinationProgressBar.visibility = View.GONE
                vaccinationList(renderState.reg)
            }
            is AdvanceVaccinationState.ShowError -> {

                Log.d("josuecitoxd", "error: " + renderState.reg.message)
            }
        }
    }

    private fun vaccinationList(list: List<AdvanceVaccinationResponseModel>) {
        advanceVaccinationAdapter = AdvanceVaccinationAdapter(context as Activity, list)
        advanceVaccinationRecyclerView.adapter = advanceVaccinationAdapter
        advanceVaccinationRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

}