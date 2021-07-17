package com.durand.vacunacionperu.ui.close

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.durand.helper.base.BaseFragment
import com.durand.vacunacionperu.MenuActivity
import com.durand.vacunacionperu.R
import kotlinx.android.synthetic.*

class CloseFragment : BaseFragment() {
    var dialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_advance_vaccination, container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog = Dialog(context as Activity)
        showPopupCloseApp()
    }


    private fun showPopupCloseApp() {
        dialog!!.setContentView(R.layout.popup_close_app)
        val salirButton: Button = dialog!!.findViewById<View>(R.id.salirButton) as Button
        salirButton.setOnClickListener {
            dialog!!.dismiss()
            requireActivity().finishAndRemoveTask()
            clearFindViewByIdCache()
        }
        val noCloseButton: Button = dialog!!.findViewById<View>(R.id.noCloseButton) as Button
        noCloseButton.setOnClickListener {
            dialog!!.dismiss()
            val intent = Intent(context, MenuActivity::class.java)
            startActivity(intent)
        }

        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.show()

    }


}