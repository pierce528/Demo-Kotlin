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
    private var dayButton: Button? = null
    private var hourButton: Button? = null
    private var dayEditText: EditText? = null
    private var hourEditText: EditText? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.add_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(AddViewModel::class.java)
        dayButton = requireView().findViewById<View>(R.id.dayBtn) as Button
        hourButton = requireView().findViewById<View>(R.id.hourBtn) as Button
        dayEditText = requireView().findViewById<View>(R.id.dayInput) as EditText
        hourEditText = requireView().findViewById<View>(R.id.hourInput) as EditText
        dayButton!!.setOnClickListener {
            mViewModel.addNewPass(0, Integer.valueOf(dayEditText!!.text.toString()))
            requireActivity().supportFragmentManager.popBackStack()
        }
        hourButton!!.setOnClickListener {
            mViewModel.addNewPass(1, Integer.valueOf(hourEditText!!.text.toString()))
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}