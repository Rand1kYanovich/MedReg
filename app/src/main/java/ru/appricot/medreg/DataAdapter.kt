package ru.appricot.medreg

import android.opengl.Visibility
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import ru.appricot.medreg.listeners.OnCouponClickListener
import ru.appricot.medreg.model.entity.Coupon
import ru.appricot.medreg.util.RandomUtil

class DataAdapter constructor(private var couponList: ArrayList<Coupon>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<DataViewHolder>() {

    private lateinit var listener: OnCouponClickListener

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): DataViewHolder {
        val layoutView: View = LayoutInflater.from(parent.context).inflate(R.layout.coupon_item, parent, false)

        return DataViewHolder(layoutView)
    }

    override fun getItemCount(): Int {
        return couponList.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val coupon: Coupon = couponList[position]
        holder.direction.text = coupon.direction
        holder.time.text = coupon.time
        var date = coupon.date
        FirebaseDatabase.getInstance().reference.child("Doctors").child(coupon.direction)
            .child(coupon.doctor).child("2019|7|23").child("9:00").addValueEventListener(object:ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    coupon.isOpen = p0.child("status").value.toString().toBoolean()
                    Log.e("Ffff",coupon.isOpen.toString())
                    if(coupon.isOpen){
                        holder.couponNumber.text = RandomUtil.getRandomNumber()
                        holder.couponNumber.visibility = View.VISIBLE
                        holder.waitTalone.visibility = View.GONE
                        holder.store.text = "NEW"
                    }
                    else{
                        holder.couponNumber.visibility = View.GONE
                        holder.waitTalone.visibility = View.VISIBLE
                        holder.store.text = "WAIT"
                    }
                }
            })
        date = date.split('|')[2]
        holder.date.text = date


        holder.bind(position, listener, couponList)
    }

    fun setList(list: ArrayList<Coupon>) {
        couponList = list
        notifyDataSetChanged()
    }

    fun setListener(listener: OnCouponClickListener) {
        this.listener = listener
    }
}