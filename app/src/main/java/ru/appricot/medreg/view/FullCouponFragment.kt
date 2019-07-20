package ru.appricot.medreg.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.appricot.medreg.R
import ru.appricot.medreg.model.entity.Coupon

class FullCouponFragment:Fragment() {

    companion object{
        fun newInstance(key:String,coupon:Coupon):FullCouponFragment{
            val fullCouponFragment = FullCouponFragment()
            val bundle = Bundle()
            bundle.putSerializable(key,coupon)
            fullCouponFragment.arguments = bundle
            return fullCouponFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.coupon_fragment, container, false)

        return rootView

    }
}