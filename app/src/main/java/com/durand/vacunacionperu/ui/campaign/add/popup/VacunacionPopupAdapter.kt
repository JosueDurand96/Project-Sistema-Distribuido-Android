package com.durand.vacunacionperu.ui.campaign.add.popup

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.durand.domain.model.local_vaccination.LocalVaccinationResponseModel
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.vacunacionperu.R

class VacunacionPopupAdapter(
    private val activity: Activity,
    private val listLine: List<VaccinationResponseModel>
) :
    RecyclerView.Adapter<VacunacionPopupAdapter.LineViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineViewHolder {
        return LineViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_on_favorites,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listLine.size
    }

    private var mOnClickSelectedPedidosPendientes: OnClickSelectedPedidosPendientes? = null


    override fun onBindViewHolder(holder: LineViewHolder, position: Int) {

        holder.nameTextView.text = listLine[position].s_nombre

        holder.lineIdLinearLayout.setOnClickListener {
            mOnClickSelectedPedidosPendientes?.onSelectPedidosPendientes(
                listLine[position].id_vacuna!!,
                listLine[position].s_nombre!!
            )
        }
    }

    class LineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        var lineIdLinearLayout: LinearLayout = itemView.findViewById(R.id.lineIdLinearLayout)
    }

    fun setListenerItemSelectedLocal(setOnClickSelectedPedidosPendientes: OnClickSelectedPedidosPendientes) {
        mOnClickSelectedPedidosPendientes = setOnClickSelectedPedidosPendientes
    }

    interface OnClickSelectedPedidosPendientes {
        fun onSelectPedidosPendientes(codigo: Int, name: String)
    }

}