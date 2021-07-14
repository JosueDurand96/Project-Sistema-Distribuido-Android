package com.durand.vacunacionperu.ui.campaign

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.durand.domain.model.campaign.CampaignResponseModel
import com.durand.vacunacionperu.R

class CampaignAdapter(
        private val activity: Activity,
        private val listLine: List<CampaignResponseModel>
) :
        RecyclerView.Adapter<CampaignAdapter.LineViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineViewHolder {
        return LineViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_vaccination_list,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int {
        return listLine.size
    }


    private var mOnClickSelectedPedidosPendientes: OnClickSelectedPedidosPendientes? = null
    private var mOnClickSelectedDelete: OnClickSelectedDelete? = null

    override fun onBindViewHolder(holder: LineViewHolder, position: Int) {
        holder.nameTextView.text = listLine[position].s_nombre

        val date: String = listLine[position].d_fec_inicio!!.substring(0, 10)
        holder.fabricTextView.text = date

        holder.imageDeleteImageView.setOnClickListener {
            mOnClickSelectedDelete?.onSelectDelete(
                    listLine[position].id_campana
            )
        }
        holder.onlickConstraintLayout.setOnClickListener {
            mOnClickSelectedPedidosPendientes?.onSelectPedidosPendientes(
                listLine[position].id_campana,
                listLine[position].id_vacuna!!,
                listLine[position].id_local!!
            )
        }


    }

    class LineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        var fabricTextView: TextView = itemView.findViewById(R.id.fabricTextView)
        var onlickConstraintLayout:ConstraintLayout = itemView.findViewById(R.id.onlickConstraintLayout)
        var imageDeleteImageView: ImageView = itemView.findViewById(R.id.imageDeleteImageView)
    }

    fun setListenerItemSelected(setOnClickSelectedPedidosPendientes: OnClickSelectedPedidosPendientes) {
        mOnClickSelectedPedidosPendientes = setOnClickSelectedPedidosPendientes
    }

    fun setListenerItemDelete(setOnClickSelectedDelete: OnClickSelectedDelete) {
        mOnClickSelectedDelete = setOnClickSelectedDelete
    }


    interface OnClickSelectedPedidosPendientes {
        fun onSelectPedidosPendientes(
            id_campana: Int?,
            id_vacuna: Int,
            id_local: Int
        )
    }

    interface OnClickSelectedDelete {
        fun onSelectDelete(
            id: Int?
        )
    }

}