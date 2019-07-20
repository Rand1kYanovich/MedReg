package ru.appricot.medreg

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import ru.appricot.medreg.listeners.OnCouponClickListener
import ru.appricot.medreg.model.entity.Coupon

class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val couponNumber: TextView = itemView.findViewById(R.id.tvCouponNumber)
    val time: TextView = itemView.findViewById(R.id.tvTime)
    val direction: TextView = itemView.findViewById(R.id.tvDirection)
    val doctor: TextView = itemView.findViewById(R.id.tvDoctor)
    val adress: TextView = itemView.findViewById(R.id.tvAdress)

    fun bind(position: Int, clickListener: OnCouponClickListener, couponList: ArrayList<Coupon>) {
        itemView.setOnClickListener { clickListener.onCouponClickListener(it, position, couponList) }
    }

}