package com.durand.vacunacionperu.ui.campaign.add

import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.durand.vacunacionperu.R
import kotlinx.android.synthetic.main.add_campaign_fragment.*
import java.util.*

class AddCampaignFragment : Fragment() {

    companion object {
        fun newInstance() = AddCampaignFragment()
    }

    private lateinit var viewModel: AddCampaignViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_campaign_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddCampaignViewModel::class.java)

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


    }

}