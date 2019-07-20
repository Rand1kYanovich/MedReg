package ru.appricot.medreg.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import ru.appricot.medreg.listeners.OnCouponClickListener
import ru.appricot.medreg.model.entity.Coupon
import ru.appricot.medreg.model.firebase.FirebaseRepository
import ru.appricot.medreg.util.FirebaseUtil
import ru.appricot.medreg.util.FragmentUtil
import ru.appricot.medreg.util.SharedUtil

class RegistrationViewModel : ViewModel() {

    var couponList = MutableLiveData<ArrayList<Coupon>>()


    fun getFirstData():LiveData<ArrayList<Coupon>>{
        if(couponList.value == null){
            couponList = FirebaseRepository.newInstance().getData()
        }
        return couponList
    }

    fun setRegistrationClickListener(etPassword: EditText, etPhoneNumber: EditText) {

        val password = etPassword.text.toString()
        SharedUtil.setUser(etPhoneNumber.text.toString())
        FirebaseUtil.setPhoneNumber()
        FirebaseUtil.setPassword(password)
    }

    fun setPersonInfoClickListener(etName: EditText, etFamily: EditText,etPatronymic:EditText){

        val name = etName.text.toString()
        val family = etFamily.text.toString()
        val patronymic = etPatronymic.text.toString()

        FirebaseUtil.setName(name,family,patronymic)
    }

}