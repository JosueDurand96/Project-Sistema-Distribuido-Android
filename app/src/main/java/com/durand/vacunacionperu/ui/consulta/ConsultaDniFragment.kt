package com.durand.vacunacionperu.ui.consulta

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.durand.domain.model.campaign.CampaignResponseModel
import com.durand.domain.model.consulta.ConsultaResponseModel
import com.durand.helper.base.BaseFragment
import com.durand.vacunacionperu.R
import com.durand.vacunacionperu.ui.campaign.CampaignAdapter
import com.durand.vacunacionperu.ui.campaign.CampaignState
import com.durand.vacunacionperu.util.ScreenState
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.consulta_dni_fragment.*
import kotlinx.android.synthetic.main.fragment_campaign.*

class ConsultaDniFragment : BaseFragment() {

    companion object {
        fun newInstance() = ConsultaDniFragment()
    }

    private lateinit var viewModel: ConsultaDniViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ConsultaDniViewModel::class.java)
        val root = inflater.inflate(R.layout.consulta_dni_fragment, container, false)



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(::getLifecycle, ::getVaccination)

        loginButton.setOnClickListener {
            viewModel.getConsultaDni(dniEditText.text.toString())
            answerTextView.text = ""
        }
    }


    private fun getVaccination(screenState: ScreenState<ConsultaState>) {
        consultaProgressBar.visibility = View.VISIBLE
        when (screenState) {

            is ScreenState.Render -> registerProcessRenderState(screenState.renderState)
        }
    }

    private fun registerProcessRenderState(renderState: ConsultaState) {
        when (renderState) {
            is ConsultaState.ShowSuccess -> {
                consultaProgressBar.visibility = View.GONE
                if (renderState.reg.success == true) {
                    answerTextView.text = renderState.reg.data!!.nombre_completo
                } else if (renderState.reg.success == false) {
                    answerTextView.text = "No encontramos resultados!"
                }
            }
            is ConsultaState.ShowError -> {
                consultaProgressBar.visibility = View.GONE
            }
        }
    }


}