package ru.appricot.medreg

import android.opengl.Visibility
import android.support.v7.widget.RecyclerView
import android.util.Log
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
        holder.direction.text = coupon.direction
        Log.e("DIrection",coupon.direction)
        holder.time.text = coupon.time
        var date = coupon.date
        date = date.split('|')[2]
        holder.date.text = date

        if(coupon.isOpen){
            holder.couponNumber.text = coupon.couponNumber
            holder.waitTalone.visibility = View.GONE
            holder.store.text = "NEW"
        }
        else{

            holder.couponNumber.visibility = View.GONE
            holder.waitTalone.visibility = View.VISIBLE
            holder.store.text = "WAIT"
        }
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