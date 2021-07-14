package com.durand.vacunacionperu.ui.campaign.add

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.durand.domain.model.local_vaccination.LocalVaccinationResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.domain.request.CampaignRequest
import com.durand.vacunacionperu.R
import com.durand.vacunacionperu.ui.campaign.add.popup.LocalVacunacionPopupAdapter
import com.durand.vacunacionperu.ui.campaign.add.popup.VacunacionPopupAdapter
import com.durand.vacunacionperu.ui.local_vaccination.LocalVaccinationState
import com.durand.vacunacionperu.ui.local_vaccination.LocalVaccinationViewModel
import com.durand.vacunacionperu.ui.vaccination.VaccinationState
import com.durand.vacunacionperu.ui.vaccination.VaccinationViewModel
import com.durand.vacunacionperu.util.ScreenState
import com.google.android.material.snackbar.Snackbar
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
    private lateinit var postCampaignViewModel: PostCampaignViewModel
    private var id_vacuna: Int? = null
    private var id_local: Int? = null
    private lateinit var mroot: ConstraintLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(AddCampaignViewModel::class.java)
        localVaccinationViewModel =
            ViewModelProvider(this).get(LocalVaccinationViewModel::class.java)
        vaccinationViewModel = ViewModelProvider(this).get(VaccinationViewModel::class.java)
        postCampaignViewModel = ViewModelProvider(this).get(PostCampaignViewModel::class.java)


        val root = inflater.inflate(R.layout.add_campaign_fragment, container, false)
        mroot = root.findViewById(R.id.addCampaignFragment)
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dateImageView.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(requireContext(), { _, yearS, monthOfYear, dayOfMonth ->
                val mesT = monthOfYear + 1
                var mesReal = ""
                if (mesT < 10){
                    mesReal = "0$mesT"
                    Log.d("sumaaa","es menor a 10")
                }else{
                    mesReal = mesT.toString()
                    Log.d("sumaaa","es mayoooor a 10")
                }
                dateTextInputEditText.setText("$yearS-$mesReal-$dayOfMonth")
            }, year, month, day)
            dpd.show()
        }
        //local de vacunacion
        localVaccinationViewModel.state.observe(::getLifecycle, ::getLocalVaccination)
        vaccinationViewModel.state.observe(::getLifecycle, ::getVaccination)
        postCampaignViewModel.state.observe(::getLifecycle, ::postCampaign)

        localVacunacionTextView.setOnClickListener {
            localVaccinationViewModel.getVaccination()
        }
        vacunacionTextView.setOnClickListener {
            vaccinationViewModel.getVaccination()
        }
        campanaButton.setOnClickListener {
            try {
                postCampaignViewModel.postCampaign(
                    CampaignRequest(
                        nameEditText.text.toString(),
                        dateTextInputEditText.text.toString(),
                        id_vacuna,
                        id_local,
                        cantidadAplicacionEditText.text.toString().toInt(),
                        false,
                        "medico@sistema.pe"
                    )
                )
            }catch (e:Exception){
                Snackbar.make(mroot, "No se puede agregar falta campos!", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.RED).show()
            }

        }

    }

    private fun postCampaign(screenState: ScreenState<PostCampaignState>) {
        when (screenState) {
            is ScreenState.Render -> postProcessRenderState(screenState.renderState)
        }
    }

    private fun postProcessRenderState(renderState: PostCampaignState) {
        when (renderState) {
            is PostCampaignState.ShowSuccess -> {
                Snackbar.make(mroot!!, "Agregado correctamente!", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.GREEN).show()
                finish()
            }
            is PostCampaignState.ShowError -> {
            }
        }
    }

    private fun finish() {
        val duration_splash = 1500
        Handler().postDelayed({

            findNavController().navigate(R.id.nav_campaign)

        }, duration_splash.toLong())

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
        val popupFavoritesRecyclerView =
            dialog2!!.findViewById<View>(R.id.popupFavoritesRecyclerView) as RecyclerView
        vacunacionPopupAdapter = VacunacionPopupAdapter(context as Activity, list)
        popupFavoritesRecyclerView.adapter = vacunacionPopupAdapter
        popupFavoritesRecyclerView.layoutManager = LinearLayoutManager(context)
        vacunacionPopupAdapter.setListenerItemSelectedLocal(object :
            VacunacionPopupAdapter.OnClickSelectedPedidosPendientes {
            override fun onSelectPedidosPendientes(codigo: Int, name: String) {
                vacunacionTextView.text = name
                id_vacuna = codigo
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
                id_local = codigo
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