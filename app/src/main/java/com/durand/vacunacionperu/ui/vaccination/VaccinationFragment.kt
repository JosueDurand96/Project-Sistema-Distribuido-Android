package com.durand.vacunacionperu.ui.vaccination

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.helper.base.BaseFragment
import com.durand.vacunacionperu.R
import com.durand.vacunacionperu.util.ScreenState
import kotlinx.android.synthetic.main.fragment_vaccination.*

class VaccinationFragment : BaseFragment() {

    private lateinit var vaccinationViewModel: VaccinationViewModel
    private lateinit var vaccinationAdapter: VaccinationAdapter
    private lateinit var vaccinationRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vaccinationViewModel =
            ViewModelProvider(this).get(VaccinationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_vaccination, container, false)
        vaccinationRecyclerView = root.findViewById(R.id.vaccinationRecyclerView)
//        val textView: TextView = root.findViewById(R.id.text_slideshow)
//        vaccinationViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGetVaccination()
        vaccinationViewModel.state.observe(::getLifecycle, ::getVaccination)
    }
    private fun initGetVaccination(){
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
                Log.d("josuecitoxd", "ShowSuccess: ${renderState.reg[0].s_nombre}")
                vaccinationProgressBar.visibility = View.GONE
                vaccinationList(renderState.reg)
            }
            is VaccinationState.ShowError -> {

                Log.d("josuecitoxd", "error: " + renderState.reg.message)
            }
        }
    }

    private fun vaccinationList(list: List<VaccinationResponseModel>) {
        vaccinationAdapter = VaccinationAdapter(context as Activity, list)
        vaccinationRecyclerView.adapter = vaccinationAdapter
        vaccinationRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


    }
}