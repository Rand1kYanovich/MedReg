package ru.appricot.medreg

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ru.appricot.medreg.util.FragmentUtil
import ru.appricot.medreg.view.RegistrationFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FragmentUtil.replace(supportFragmentManager,R.id.content, RegistrationFragment.newInstance())
    }
}
