package ru.appricot.medreg

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import ru.appricot.medreg.util.FragmentUtil
import ru.appricot.medreg.util.SharedUtil
import ru.appricot.medreg.view.CouponFragment
import ru.appricot.medreg.view.RegistrationFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this);
        SharedUtil.init()
        setContentView(R.layout.activity_main)
        if (SharedUtil.getUser() == "")
            FragmentUtil.replace(supportFragmentManager, R.id.content, RegistrationFragment.newInstance())
        else {
            FragmentUtil.replace(supportFragmentManager, R.id.content, CouponFragment.newInstance())
        }
    }

    override fun onDestroy() {
        FirebaseDatabase.getInstance().reference.child("Users").child(SharedUtil.getUser()).child("coupons").child("isHere").setValue(false)
        super.onDestroy()
    }
}
