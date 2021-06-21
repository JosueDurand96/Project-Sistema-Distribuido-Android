package com.durand.vacunacionperu.ui.vaccination

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.durand.domain.model.vaccination.VaccinationResponseModel
import com.durand.vacunacionperu.R


class VaccinationAdapter(
    private val activity: Activity,
    private val listLine: List<VaccinationResponseModel>
) :
    RecyclerView.Adapter<VaccinationAdapter.LineViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineViewHolder {
        return LineViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_vaccination,
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

        holder.fabricTextView.text = listLine[position].s_fabricante
        holder.nameTextView.text = listLine[position].s_nombre
//        holder.buttonConstraintLayout.setOnClickListener {
//            mOnClickSelectedPedidosPendientes?.onSelectPedidosPendientes(
//                    listLine[position].id,
//                    listLine[position].title!!
//            )
//        }

    }

    class LineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        var fabricTextView: TextView = itemView.findViewById(R.id.fabricTextView)

    }

    fun setListenerItemSelected(setOnClickSelectedPedidosPendientes: OnClickSelectedPedidosPendientes) {
        mOnClickSelectedPedidosPendientes = setOnClickSelectedPedidosPendientes
    }

    interface OnClickSelectedPedidosPendientes {
        fun onSelectPedidosPendientes(id: Int?, name: String)
    }

}