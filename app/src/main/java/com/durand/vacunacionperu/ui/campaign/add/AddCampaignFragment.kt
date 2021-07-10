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
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.durand.domain.model.local_vaccination.LocalVaccinationResponseModel
import com.durand.vacunacionperu.R
import com.durand.vacunacionperu.room.AppLocalDatabase
import com.durand.vacunacionperu.room.LocalVacunacion
import com.durand.vacunacionperu.room.LocalVacunacionDao
import com.durand.vacunacionperu.ui.local_vaccination.LocalVaccinationState
import com.durand.vacunacionperu.ui.local_vaccination.LocalVaccinationViewModel
import com.durand.vacunacionperu.util.ScreenState
import kotlinx.android.synthetic.main.add_campaign_fragment.*
import kotlinx.android.synthetic.main.fragment_vaccination.*
import java.util.*
import kotlin.collections.ArrayList


class AddCampaignFragment : Fragment() {

    private lateinit var localVaccinationViewModel: LocalVaccinationViewModel
    private lateinit var viewModel: AddCampaignViewModel
    var dialog: Dialog? = null
    private lateinit var favoritesPopupAdapter:FavoritesPopupAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(AddCampaignViewModel::class.java)
        localVaccinationViewModel = ViewModelProvider(this).get(LocalVaccinationViewModel::class.java)

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

        localVacunacionTextView.setOnClickListener {
            localVaccinationViewModel.getVaccination()
        }


    }




    private fun getLocalVaccination(screenState: ScreenState<LocalVaccinationState>) {
        when (screenState) {
            is ScreenState.Render -> registerProcessRenderState(screenState.renderState)
        }
    }

    private fun registerProcessRenderState(renderState: LocalVaccinationState) {
        when (renderState) {
            is LocalVaccinationState.ShowSuccess -> {
                dialog = Dialog(context as Activity)
                ShowPopupFavorites(renderState.reg)
            }
            is LocalVaccinationState.ShowError -> {
                Log.d("josuecitoxd", "error: " + renderState.reg.message)
            }
        }
    }

    fun ShowPopupFavorites(list: List<LocalVaccinationResponseModel>) {

        dialog!!.setContentView(R.layout.popup_favorites_custom)

        val popupFavoritesRecyclerView = dialog!!.findViewById<View>(R.id.popupFavoritesRecyclerView) as RecyclerView
        favoritesPopupAdapter = FavoritesPopupAdapter(context as Activity, list)
        popupFavoritesRecyclerView.adapter = favoritesPopupAdapter
        popupFavoritesRecyclerView.layoutManager = LinearLayoutManager(context)


        val cerrarPopup: ImageView = dialog!!.findViewById<View>(R.id.cerrarPopup) as ImageView
        cerrarPopup.setOnClickListener {
            dialog!!.dismiss()
        }

        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.show()

    }
}