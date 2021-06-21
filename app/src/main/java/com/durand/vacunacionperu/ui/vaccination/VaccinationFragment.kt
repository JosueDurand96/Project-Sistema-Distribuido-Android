package com.durand.vacunacionperu.ui.vaccination

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.durand.helper.base.BaseFragment
import com.durand.vacunacionperu.R
import com.durand.vacunacionperu.util.ScreenState

class VaccinationFragment : BaseFragment() {

    private lateinit var vaccinationViewModel: VaccinationViewModel
    private lateinit var vaccinationAdapter :VaccinationAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vaccinationViewModel =
            ViewModelProvider(this).get(VaccinationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_vaccination, container, false)
//        val textView: TextView = root.findViewById(R.id.text_slideshow)
//        vaccinationViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vaccinationViewModel.getVaccination()
        vaccinationViewModel.state.observe(::getLifecycle, ::getVaccination)
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
               // VaccinationState(renderState.reg)
            }
            is VaccinationState.ShowError -> {
                Log.d("josuecitoxd", "error: " + renderState.reg.message)
            }
        }
    }
}