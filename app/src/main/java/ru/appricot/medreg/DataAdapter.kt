package ru.appricot.medreg

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.appricot.medreg.listeners.OnCouponClickListener
import ru.appricot.medreg.model.entity.Coupon

class DataAdapter constructor(private var couponList: ArrayList<Coupon>) : RecyclerView.Adapter<DataViewHolder>() {

    private lateinit var listener:OnCouponClickListener

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): DataViewHolder {
        val layoutView: View = LayoutInflater.from(parent.context).inflate(R.layout.coupon_item, parent, false)

        return DataViewHolder(layoutView)
    }

    override fun getItemCount(): Int {
        return couponList.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val coupon:Coupon = couponList[position]
        holder.couponNumber.text = coupon.couponNumber
        holder.adress.text = coupon.adress
        holder.direction.text = coupon.direction
        holder.doctor.text = coupon.doctor
        holder.time.text = coupon.time

        holder.bind(position,listener,couponList)
    }

    fun setList(list: ArrayList<Coupon>) {
        couponList = list
        notifyDataSetChanged()
    }

    fun setListener(listener:OnCouponClickListener){
        this.listener = listener
    }
}