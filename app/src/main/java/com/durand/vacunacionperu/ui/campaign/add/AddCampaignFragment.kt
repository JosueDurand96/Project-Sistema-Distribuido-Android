package com.durand.vacunacionperu.ui.campaign.add

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.durand.domain.model.local_vaccination.LocalVaccinationResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.vacunacionperu.R
import com.durand.vacunacionperu.ui.campaign.add.popup.LocalVacunacionPopupAdapter
import com.durand.vacunacionperu.ui.campaign.add.popup.VacunacionPopupAdapter
import com.durand.vacunacionperu.ui.local_vaccination.LocalVaccinationState
import com.durand.vacunacionperu.ui.local_vaccination.LocalVaccinationViewModel
import com.durand.vacunacionperu.ui.vaccination.VaccinationState
import com.durand.vacunacionperu.ui.vaccination.VaccinationViewModel
import com.durand.vacunacionperu.util.ScreenState
import kotlinx.android.synthetic.main.add_campaign_fragment.*
import kotlinx.android.synthetic.main.fragment_vaccination.*
import java.util.*


class AddCampaignFragment : Fragment() {
    private lateinit var vaccinationViewModel: VaccinationViewModel
    private lateinit var localVaccinationViewModel: LocalVaccinationViewModel
    private lateinit var viewModel: AddCampaignViewModel
    var dialog: Dialog? = null
    var dialog2: Dialog? = null
    private lateinit var favoritesPopupAdapter: LocalVacunacionPopupAdapter
    private lateinit var vacunacionPopupAdapter: VacunacionPopupAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(AddCampaignViewModel::class.java)
        localVaccinationViewModel =
            ViewModelProvider(this).get(LocalVaccinationViewModel::class.java)
        vaccinationViewModel = ViewModelProvider(this).get(VaccinationViewModel::class.java)

        val root = inflater.inflate(R.layout.add_campaign_fragment, container, false)

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dateImageView.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(requireContext(), { _, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                dateTextInputEditText.setText("$dayOfMonth $monthOfYear, $year")
            }, year, month, day)
            dpd.show()
        }
        //local de vacunacion
        localVaccinationViewModel.state.observe(::getLifecycle, ::getLocalVaccination)
        vaccinationViewModel.state.observe(::getLifecycle, ::getVaccination)

        localVacunacionTextView.setOnClickListener {
            localVaccinationViewModel.getVaccination()
        }
        vacunacionTextView.setOnClickListener {
            vaccinationViewModel.getVaccination()
        }


    }


    private fun getVaccination(screenState: ScreenState<VaccinationState>) {
        when (screenState) {
            is ScreenState.Render -> vacunaProcessRenderState(screenState.renderState)
        }
    }

    private fun vacunaProcessRenderState(renderState: VaccinationState) {
        when (renderState) {
            is VaccinationState.ShowSuccess -> {
                dialog2 = Dialog(context as Activity)
                vaccinationList(renderState.reg)

            }
            is VaccinationState.ShowError -> {

            }
        }
    }

    private fun vaccinationList(list: List<VaccinationResponseModel>) {
        dialog2!!.setContentView(R.layout.popup_favorites_custom2)
        val popupFavoritesRecyclerView = dialog2!!.findViewById<View>(R.id.popupFavoritesRecyclerView) as RecyclerView
        vacunacionPopupAdapter = VacunacionPopupAdapter(context as Activity, list)
        popupFavoritesRecyclerView.adapter = vacunacionPopupAdapter
        popupFavoritesRecyclerView.layoutManager = LinearLayoutManager(context)
        vacunacionPopupAdapter.setListenerItemSelectedLocal(object :
            VacunacionPopupAdapter.OnClickSelectedPedidosPendientes {
            override fun onSelectPedidosPendientes(codigo: Int, name: String) {
                vacunacionTextView.text = name
                dialog2!!.dismiss()
            }
        })

        val cerrarPopup: ImageView = dialog2!!.findViewById<View>(R.id.cerrarPopup) as ImageView
        cerrarPopup.setOnClickListener {
            dialog2!!.dismiss()
        }

        dialog2!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog2!!.show()

    }


    private fun getLocalVaccination(screenState: ScreenState<LocalVaccinationState>) {
        when (screenState) {
            is ScreenState.Render -> localProcessRenderState(screenState.renderState)
        }
    }

    private fun localProcessRenderState(renderState: LocalVaccinationState) {
        when (renderState) {
            is LocalVaccinationState.ShowSuccess -> {
                dialog = Dialog(context as Activity)
                ShowPopupLocal(renderState.reg)
            }
            is LocalVaccinationState.ShowError -> {
                Log.d("josuecitoxd", "error: " + renderState.reg.message)
            }
        }
    }

    fun ShowPopupLocal(list: List<LocalVaccinationResponseModel>) {

        dialog!!.setContentView(R.layout.popup_favorites_custom)

        val popupFavoritesRecyclerView =
            dialog!!.findViewById<View>(R.id.popupFavoritesRecyclerView) as RecyclerView
        favoritesPopupAdapter = LocalVacunacionPopupAdapter(context as Activity, list)
        popupFavoritesRecyclerView.adapter = favoritesPopupAdapter
        popupFavoritesRecyclerView.layoutManager = LinearLayoutManager(context)
        favoritesPopupAdapter.setListenerItemSelectedLocal(object :
            LocalVacunacionPopupAdapter.OnClickSelectedPedidosPendientes {
            override fun onSelectPedidosPendientes(codigo: Int, name: String) {
                localVacunacionTextView.text = name
                dialog!!.dismiss()
            }
        })

        val cerrarPopup: ImageView = dialog!!.findViewById<View>(R.id.cerrarPopup) as ImageView
        cerrarPopup.setOnClickListener {
            dialog!!.dismiss()
        }

        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.show()

    }
}