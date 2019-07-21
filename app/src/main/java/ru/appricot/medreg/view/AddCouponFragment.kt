package ru.appricot.medreg.view

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
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
        val btnYellow: Button = rootView.findViewById(R.id.btnYellow)
        val btnBlue: Button = rootView.findViewById(R.id.btnBlue)


        btnYellow.setOnClickListener {
            val coupon = Coupon()
            coupon.doctor = doctor
            coupon.direction = direction
            coupon.time = btnYellow.text.toString()
            Log.e("HI",coupon.time)
            coupon.date = date
            coupon.couponNumber = RandomUtil.getRandomNumber()
            FirebaseUtil.setCoupon(coupon)
            Log.e("Coupon",coupon.toString())
            FragmentUtil.replace(activity!!.supportFragmentManager,R.id.content,CouponFragment.newInstance())
            viewModelRegistration.addCoupon(coupon)
        }

        btnBlue.setOnClickListener {
            val coupon = Coupon()
            coupon.doctor = doctor
            coupon.direction = direction
            coupon.time = btnBlue.text.toString()
            coupon.date = date
            coupon.couponNumber = RandomUtil.getRandomNumber()
            FirebaseUtil.setCoupon(coupon)
            Log.e("Coupon",coupon.toString())
            FragmentUtil.replace(activity!!.supportFragmentManager,R.id.content,CouponFragment.newInstance())
            viewModelRegistration.addCoupon(coupon)
        }
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val yearE = year +1
            date = "$yearE|$month|$dayOfMonth"
            Log.e("Date",date)
        }

        val arrayDirections = viewModelCoupon.getDirectionArray()
        arrayDirections.observe(viewLifecycleOwner, Observer {
            val adapterDirection =
                ArrayAdapter<String>(context!!, R.layout.simple_spinner_item, arrayDirections.value!!)
            adapterDirection.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            spinnerDirection.adapter = adapterDirection
            Log.e("Fuck","Shit")

        })



        spinnerDirection.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                direction = arrayDirections.value!![position]
                Log.e("Direction",direction)
                val arrayDoctors = viewModelCoupon.getDoctorsArray(arrayDirections.value!![position])

                arrayDoctors.observe(viewLifecycleOwner, Observer {
                    val adapterDoctors =
                        ArrayAdapter<String>(context!!, R.layout.simple_spinner_item, arrayDoctors.value!!)
                    adapterDoctors.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
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