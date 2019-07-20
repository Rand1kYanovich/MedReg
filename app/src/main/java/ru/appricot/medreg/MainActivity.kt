package ru.appricot.medreg

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.appricot.medreg.util.FragmentUtil
import ru.appricot.medreg.util.SharedUtil
import ru.appricot.medreg.view.CouponFragment
import ru.appricot.medreg.view.RegistrationFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SharedUtil.init()
        setContentView(R.layout.activity_main)
        if (SharedUtil.getUser() == "")
            FragmentUtil.replace(supportFragmentManager, R.id.content, RegistrationFragment.newInstance())
        else FragmentUtil.replace(supportFragmentManager,R.id.content,CouponFragment.newInstance())
    }
}
