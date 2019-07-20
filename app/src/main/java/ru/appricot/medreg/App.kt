package ru.appricot.medreg

import android.app.Application
import android.content.Context


class App : Application() {


    companion object {
        private lateinit var instance: App
        private fun getApplication(): App =instance
        fun getContext():Context = getApplication().applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }





}