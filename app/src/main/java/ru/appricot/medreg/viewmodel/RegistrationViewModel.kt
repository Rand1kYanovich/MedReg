package ru.appricot.medreg.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.provider.ContactsContract
import android.util.Log
import android.widget.EditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import ru.appricot.medreg.model.entity.Coupon
import ru.appricot.medreg.util.FirebaseUtil
import ru.appricot.medreg.util.SharedUtil

class RegistrationViewModel : ViewModel() {

    var couponList = MutableLiveData<ArrayList<Coupon>>()


    fun getFirstData(): LiveData<ArrayList<Coupon>> {
        if (couponList.value == null) {
            val list = ArrayList<Coupon>()
            FirebaseDatabase.getInstance().reference.child("Users").child(SharedUtil.getUser()).child("coupons")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (data: DataSnapshot in dataSnapshot.children) {
                                val coupon = Coupon()
                                coupon.couponNumber = data.child("couponNumber").value.toString()
                                coupon.direction = data.child("direction").value.toString()
                                coupon.doctor = data.child("doctor").value.toString()
                                coupon.time = data.child("time").value.toString()
                                coupon.date = data.child("date").value.toString()
                                list.add(coupon)
                                couponList.value = list
                            }
                        }
                    }
                })

            couponList.value = list
        }
        return couponList
    }

    fun setRegistrationClickListener(etPassword: EditText, etPhoneNumber: EditText) {

        val password = etPassword.text.toString()
        Log.e("Number", etPhoneNumber.text.toString())
        SharedUtil.setUser(etPhoneNumber.text.toString())
        FirebaseUtil.setPassword(password)
    }

    fun setPersonInfoClickListener(etName: EditText, etFamily: EditText, etPatronymic: EditText) {

        val name = etName.text.toString()
        val family = etFamily.text.toString()
        val patronymic = etPatronymic.text.toString()

        FirebaseUtil.setName(name, family, patronymic)
    }

    fun addCoupon(coupon: Coupon) {
        if (couponList.value != null) {
            val list = couponList.value!!
            list.add(coupon)
            couponList.value = list
        } else {
            val list = ArrayList<Coupon>()
            list.add(coupon)
            couponList.value = list
        }
    }



}