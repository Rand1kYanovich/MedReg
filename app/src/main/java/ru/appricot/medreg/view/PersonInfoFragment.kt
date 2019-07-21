package ru.appricot.medreg.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import ru.appricot.medreg.R
import ru.appricot.medreg.util.FragmentUtil
import ru.appricot.medreg.viewmodel.RegistrationViewModel


class PersonInfoFragment : Fragment() {

    private val viewModel: RegistrationViewModel by lazy {
        ViewModelProviders.of(activity!!).get(RegistrationViewModel::class.java)
    }

    companion object {
        fun newInstance(): PersonInfoFragment = PersonInfoFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.person_info_fragment, container, false)
        val etName: EditText = rootView.findViewById(R.id.etName)
        val etFamily: EditText = rootView.findViewById(R.id.etFamily)
        val etPatronymic:EditText = rootView.findViewById(R.id.etPatronymic)
        val btnSubmit: Button = rootView.findViewById(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            viewModel.setPersonInfoClickListener(etName,etFamily,etPatronymic)
            FragmentUtil.replace(activity!!.supportFragmentManager, R.id.content, CouponFragment.newInstance())
        }


        return rootView
    }
}