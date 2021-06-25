package com.durand.vacunacionperu.ui.vaccination

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.helper.base.BaseFragment
import com.durand.vacunacionperu.R
import com.durand.vacunacionperu.ui.vaccination.add.AddVaccinationFragment
import com.durand.vacunacionperu.util.ScreenState
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_vaccination.*


class VaccinationFragment : BaseFragment() {

    private lateinit var vaccinationViewModel: VaccinationViewModel
    private lateinit var vaccinationAdapter: VaccinationAdapter
    private lateinit var vaccinationRecyclerView: RecyclerView
    private lateinit var vaccinationFab: FloatingActionButton
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vaccinationViewModel = ViewModelProvider(this).get(VaccinationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_vaccination, container, false)
        vaccinationRecyclerView = root.findViewById(R.id.vaccinationRecyclerView)
        vaccinationFab = root.findViewById(R.id.vaccinationFab)
        vaccinationFab.setOnClickListener {
            findNavController().navigate(R.id.action_nav_vaccination_to_nav_vaccinwation)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGetVaccination()
        vaccinationViewModel.state.observe(::getLifecycle, ::getVaccination)
    }

    private fun initGetVaccination() {
        vaccinationProgressBar.visibility = View.VISIBLE
        vaccinationViewModel.getVaccination()
    }


    private fun getVaccination(screenState: ScreenState<VaccinationState>) {
        when (screenState) {
            is ScreenState.Render -> registerProcessRenderState(screenState.renderState)
        }
    }

    private fun registerProcessRenderState(renderState: VaccinationState) {
        when (renderState) {
            is VaccinationState.ShowSuccess -> {
                vaccinationProgressBar.visibility = View.GONE
                vaccinationList(renderState.reg)

            }
            is VaccinationState.ShowError -> {

            }
        }
    }

    private fun vaccinationList(list: List<VaccinationResponseModel>) {
        vaccinationAdapter = VaccinationAdapter(context as Activity, list)
        vaccinationRecyclerView.adapter = vaccinationAdapter
        vaccinationRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        vaccinationAdapter.setListenerItemSelected(object :
            VaccinationAdapter.OnClickSelectedPedidosPendientes {
            override fun onSelectPedidosPendientes(id: Int?, name: String) {

            }
        })
    }
}