package com.pierce.demo.list.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pierce.demo.list.R

class AddFragment : Fragment() {
    private lateinit var mViewModel: AddViewModel
    private var mDayButton: Button? = null
    private var mHourButton: Button? = null
    private var mDayEditText: EditText? = null
    private var mHourEditText: EditText? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.add_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(this).get(AddViewModel::class.java)
        mDayButton = requireView().findViewById<View>(R.id.dayBtn) as Button
        mHourButton = requireView().findViewById<View>(R.id.hourBtn) as Button
        mDayEditText = requireView().findViewById<View>(R.id.dayInput) as EditText
        mHourEditText = requireView().findViewById<View>(R.id.hourInput) as EditText
        mDayButton!!.setOnClickListener {
            mViewModel.addNewPass(0, Integer.valueOf(mDayEditText!!.text.toString()))
            requireActivity().supportFragmentManager.popBackStack()
        }
        mHourButton!!.setOnClickListener {
            mViewModel.addNewPass(1, Integer.valueOf(mHourEditText!!.text.toString()))
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}