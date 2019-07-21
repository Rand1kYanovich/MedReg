package ru.appricot.medreg.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import ru.appricot.medreg.DataAdapter
import ru.appricot.medreg.R
import ru.appricot.medreg.listeners.OnCouponClickListener
import ru.appricot.medreg.model.entity.Coupon
import ru.appricot.medreg.util.FragmentUtil
import ru.appricot.medreg.viewmodel.RegistrationViewModel

class CouponFragment : Fragment() {

    private val viewModel: RegistrationViewModel by lazy {
        ViewModelProviders.of(activity!!).get(RegistrationViewModel::class.java)
    }


    companion object {
        fun newInstance(): CouponFragment = CouponFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.coupon_fragment, container, false)
        val couponList = viewModel.getFirstData()

        val btnAdd: Button = rootView.findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener {
            FragmentUtil.replace(activity!!.supportFragmentManager, R.id.content, AddCouponFragment.newInstance())
        }

        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        val adapter = DataAdapter(ArrayList())


        couponList.observe(viewLifecycleOwner,
            Observer<ArrayList<Coupon>> { t -> adapter.setList(t!!) })
        adapter.setListener(object : OnCouponClickListener {
            override fun onCouponClickListener(view: View, position: Int, couponList: ArrayList<Coupon>) {
                FragmentUtil.replaceWithBackStack(
                    activity!!.supportFragmentManager,
                    R.id.content,
                    FullCouponFragment.newInstance("name", couponList[position])
                )
            }

        })

        recyclerView.adapter = adapter
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.setHasFixedSize(true)




        return rootView
    }
}