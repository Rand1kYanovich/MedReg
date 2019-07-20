package ru.appricot.medreg.model.firebase

import android.arch.lifecycle.MutableLiveData
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import ru.appricot.medreg.model.entity.Coupon
import ru.appricot.medreg.util.FirebaseUtil

class FirebaseRepository {

    var couponList = MutableLiveData<ArrayList<Coupon>>()

    companion object {
        fun newInstance() = FirebaseRepository()
    }

    fun getData(): MutableLiveData<ArrayList<Coupon>> {
        val list = ArrayList<Coupon>()
        FirebaseDatabase.getInstance().reference.child(FirebaseUtil.phoneNumber).child("coupons")
            .addChildEventListener(object : ChildEventListener {
                override fun onCancelled(p0: DatabaseError) {}

                override fun onChildMoved(p0: DataSnapshot, p1: String?) {}

                override fun onChildChanged(p0: DataSnapshot, p1: String?) {}

                override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                    if (dataSnapshot.exists()) {
                        val coupon = Coupon()
                        coupon.couponNumber = dataSnapshot.child("couponNumber").value.toString()
                        coupon.adress = dataSnapshot.child("adress").value.toString()
                        coupon.direction = dataSnapshot.child("direction").value.toString()
                        coupon.doctor = dataSnapshot.child("doctor").value.toString()
                        coupon.time = dataSnapshot.child("time").value.toString()

                        list.add(coupon)
                    }
                    couponList.value = list

                }

                override fun onChildRemoved(p0: DataSnapshot) {
                }
            })
        return couponList
    }
}