package ru.appricot.medreg.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import ru.appricot.medreg.R
import ru.appricot.medreg.model.entity.Coupon
import ru.appricot.medreg.util.FirebaseUtil
import ru.appricot.medreg.util.FragmentUtil
import ru.appricot.medreg.util.RandomUtil
import ru.appricot.medreg.viewmodel.CouponViewModel
import ru.appricot.medreg.viewmodel.RegistrationViewModel

class AddCouponFragment : Fragment() {

    private var doctor = ""
    private var direction = ""
    private var date = ""
    private var time = "8:00-14:00"

    private val viewModelCoupon: CouponViewModel by lazy {
        ViewModelProviders.of(activity!!).get(CouponViewModel::class.java)
    }

    private val viewModelRegistration: RegistrationViewModel by lazy {
        ViewModelProviders.of(activity!!).get(RegistrationViewModel::class.java)
    }

    companion object {
        fun newInstance() = AddCouponFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.add_coupon_fragment, container, false)

        val spinnerDoctor: Spinner = rootView.findViewById(R.id.spinnerDoctor)
        val spinnerDirection: Spinner = rootView.findViewById(R.id.spinnerDirection)
        val calendarView: CalendarView = rootView.findViewById(R.id.calendarView)
        val btnSubmit: Button = rootView.findViewById(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            val coupon = Coupon()
            coupon.doctor = doctor
            coupon.direction = direction
            coupon.time = time
            coupon.date = date
            coupon.couponNumber = RandomUtil.getRandomNumber()
            FirebaseUtil.setCoupon(coupon)
            Log.e("Coupon",coupon.toString())
            FragmentUtil.replace(activity!!.supportFragmentManager,R.id.content,CouponFragment.newInstance())
            viewModelRegistration.addCoupon(coupon)
        }

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val day = dayOfMonth+1
            date = "$year|$month|$day"
            Log.e("Date",date)
        }

        val arrayDirections = viewModelCoupon.getDirectionArray()
        arrayDirections.observe(viewLifecycleOwner, Observer {
            val adapterDirection =
                ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_item, arrayDirections.value!!)
            spinnerDirection.adapter = adapterDirection

        })



        spinnerDirection.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                direction = arrayDirections.value!![position]
                Log.e("Direction",direction)
                val arrayDoctors = viewModelCoupon.getDoctorsArray(arrayDirections.value!![position])

                arrayDoctors.observe(viewLifecycleOwner, Observer {
                    val adapterDoctors =
                        ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_item, arrayDoctors.value!!)
                    spinnerDoctor.adapter = adapterDoctors
                    spinnerDoctor.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            doctor = arrayDoctors.value!![position]
                            Log.e("Doctor",doctor)
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {

                        }

                    }
                })
            }

        }
        return rootView
    }
}