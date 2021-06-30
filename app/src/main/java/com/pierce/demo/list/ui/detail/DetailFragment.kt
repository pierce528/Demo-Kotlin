package com.pierce.demo.list.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pierce.demo.list.R

class DetailFragment : Fragment() {
    lateinit var textView: TextView
    lateinit var mViewModel: DetailViewModel
    var mId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null) {
            mId = args.getInt("id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        textView = requireView().findViewById<View>(R.id.detail) as TextView
    }

    override fun onResume() {
        super.onResume()
        mViewModel.info.observe(viewLifecycleOwner, Observer {
            textView.setText(it)
        })
        mViewModel.loadData(mId)
    }
}