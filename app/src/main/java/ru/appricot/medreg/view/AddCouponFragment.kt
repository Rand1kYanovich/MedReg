package ru.appricot.medreg.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import ru.appricot.medreg.R
import ru.appricot.medreg.viewmodel.CouponViewModel

class AddCouponFragment:Fragment() {

    private val viewModel: CouponViewModel by lazy {
        ViewModelProviders.of(activity!!).get(CouponViewModel::class.java)
    }

    companion object{
        fun newInstance()=AddCouponFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.add_coupon_fragment, container, false)

        val spinnerDoctor:Spinner = rootView.findViewById(R.id.spinnerDoctor)
        val spinnerDirection:Spinner = rootView.findViewById(R.id.spinnerDirection)


        val arrayDirections = viewModel.getDirectionArray()
        arrayDirections.observe(viewLifecycleOwner, Observer {
            val adapterDirection = ArrayAdapter<String>(context!!,android.R.layout.simple_spinner_item,arrayDirections.value!!)
            spinnerDirection.adapter = adapterDirection
        })



        spinnerDirection.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val arrayDoctors = viewModel.getDoctorsArray(arrayDirections.value!![position])

                arrayDoctors.observe(viewLifecycleOwner, Observer {
                    val adapterDoctors = ArrayAdapter<String>(context!!,android.R.layout.simple_spinner_item,arrayDoctors.value!!)
                    spinnerDoctor.adapter = adapterDoctors
                })
            }

        }
        return rootView
    }
}