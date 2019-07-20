package ru.appricot.medreg.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import ru.appricot.medreg.App

class SharedUtil {

    companion object {
        private const val SHARED_NAME = "medRegPref"
        private lateinit var sPref: SharedPreferences

        fun init() {
            sPref = App.getContext().getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
        }

        fun getEditor(): SharedPreferences.Editor = sPref.edit()
        fun setUser(phoneNumber:String){
            getEditor().putString("id",phoneNumber).commit()
            FirebaseUtil.setPhoneNumber()
        }

        fun getUser() = sPref.getString("id","")

    }


}