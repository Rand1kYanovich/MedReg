package ru.appricot.medreg.model.firebase

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.google.firebase.database.*
import ru.appricot.medreg.model.entity.Coupon
import ru.appricot.medreg.util.FirebaseUtil
import ru.appricot.medreg.util.SharedUtil

class FirebaseRepository {

    var couponList = MutableLiveData<ArrayList<Coupon>>()

    companion object {
        fun newInstance() = FirebaseRepository()
    }


}