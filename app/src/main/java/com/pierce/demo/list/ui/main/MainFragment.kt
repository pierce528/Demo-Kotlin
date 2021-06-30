package com.pierce.demo.list.ui.main

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pierce.demo.list.R
import com.pierce.demo.list.ui.add.AddFragment
import com.pierce.demo.list.ui.detail.DetailFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val mViewModel: MainViewModel by viewModel()
    private lateinit var textView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PassAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        textView = requireView().findViewById(R.id.message)
        mViewModel.mUrlResult.observe(viewLifecycleOwner, Observer {
            textView.setText(it)
        })
    }

    fun initRecyclerView() {
        // Set up the RecyclerView.
        recyclerView = requireView().findViewById<View>(R.id.list) as RecyclerView
        adapter = PassAdapter(activity, mViewModel)
        adapter.setOnItemClickListener(object : PassAdapter.OnItemClickListener {
            override fun onItemClick(item: ListItem) {
                val nextFrag = DetailFragment()
                val bundle = Bundle()
                bundle.putInt("id", (item as PassItem).mData.id)
                nextFrag.setArguments(bundle)
                requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, nextFrag)
                        .addToBackStack(null)
                        .commit()
            }
        })
        recyclerView.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))
        recyclerView.setAdapter(adapter)
        val decor = DividerItemDecoration(context, RecyclerView.VERTICAL)
        decor.setDrawable(requireActivity().getDrawable(R.drawable.divider)!!)
        recyclerView.addItemDecoration(decor)

        // Get all the passes from the database
        // and associate them to the adapter.
        mViewModel.getAllPasses().observe(viewLifecycleOwner, Observer {
            adapter.setPasses(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_menu_items, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val selected = item.itemId
        if (selected == R.id.fragment_menu_add) {
            val nextFrag = AddFragment()
                    requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, nextFrag)
                    .addToBackStack(null)
                    .commit()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()

        //view model update trigger
        mViewModel.getUrlResult()
    }

}