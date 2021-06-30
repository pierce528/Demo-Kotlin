package com.pierce.demo.list.ui.main

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.pierce.demo.list.R
import com.pierce.demo.list.room.PassData
import java.util.*

class PassAdapter(private val mActivity: FragmentActivity?, private val mViewModel: MainViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mRecords: MutableList<ListItem> = ArrayList()
    private lateinit var onItemClickListener: OnItemClickListener
    private lateinit var mPasses: List<PassData>

    fun setPasses(list: List<PassData>) {
        mPasses = list
        updateRecords()
    }

    fun updateRecords() {
        var hourHeaderSet = false
        if (!mPasses!!.isEmpty()) {
            mRecords.clear()
            mRecords.add(HeaderItem("Day PASS"))
            for (data in mPasses!!) {
                if (data.type === 1 && !hourHeaderSet) {
                    mRecords.add(HeaderItem("HOUR PASS"))
                    hourHeaderSet = true
                }
                mRecords.add(PassItem(data))
            }
        } else {
            mRecords.add(HeaderItem("No PASS Added"))
        }
        //mActivity.runOnUiThread { notifyDataSetChanged() }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (viewType == ListItem.Type.HEADER.d) {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_header, parent, false)
            HeaderViewHolder(view)
        } else {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pass, parent, false)
            PassViewHolder(view)
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val type = getItemViewType(position)
        if (type == ListItem.Type.HEADER.d) {
            (holder as HeaderViewHolder).bind(mRecords[position])
        } else {
            (holder as PassViewHolder).bind(mRecords[position])
        }
    }

    override fun getItemCount(): Int {
        return mRecords.size
    }

    override fun getItemViewType(position: Int): Int {
        return mRecords[position].getType().d
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(item: ListItem)
    }

    private fun setButtonState(button: Button, active: Boolean) {
        //mActivity.runOnUiThread {
            button.isClickable = active
            button.text = if (active) "ACTIVATE" else "ACTIVATED"
            button.setBackgroundColor(if (active) Color.GREEN else Color.GRAY)
        //}
    }

    internal inner class PassViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var title = itemView.findViewById<TextView>(R.id.passTitle)
        var price = itemView.findViewById<TextView>(R.id.price)
        var button =
            itemView.findViewById<Button>(R.id.button)

        fun bind(item: ListItem) {
            val pass = item as PassItem
            title.setText(pass.mPassName)
            price.setText(pass.mPrice)
            itemView.setOnClickListener { onItemClickListener!!.onItemClick(item) }
            if (pass.mData.status === 0) {
                setButtonState(button, true)
                button.setOnClickListener { mViewModel.update(pass.mData) }
            } else {
                setButtonState(button, false)
            }
        }
    }

    internal inner class HeaderViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var title = itemView.findViewById<TextView>(R.id.headerTitle)
        var layout = itemView.findViewById<View>(R.id.layout)
        fun bind(item: ListItem) {
            val header = item as HeaderItem
            title.setText(header.title)
            layout.setBackgroundColor(Color.LTGRAY)
        }
    }

}