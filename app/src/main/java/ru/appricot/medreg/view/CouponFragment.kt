package ru.appricot.medreg.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.iid.FirebaseInstanceId
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

        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener { instanceIdResult ->
            val newToken = instanceIdResult.token
            Log.e("Tpke", newToken)
        }
        val recyclerView: androidx.recyclerview.widget.RecyclerView = rootView.findViewById(R.id.recyclerView)
        val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
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