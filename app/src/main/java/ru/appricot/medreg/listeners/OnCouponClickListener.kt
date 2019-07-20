package ru.appricot.medreg.listeners

import android.view.View
import ru.appricot.medreg.model.entity.Coupon

interface OnCouponClickListener {

    fun onCouponClickListener(view:View,position:Int,couponList:ArrayList<Coupon>)
}