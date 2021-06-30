package com.pierce.demo.list.ui.main

import com.pierce.demo.list.room.PassData

class PassItem(data: PassData) : ListItem() {

    var mData: PassData

    val mPassName: String
        get() =//return mPassName;
            mData.period
                .toString() + " " + if (mData.type === 0) "DAY PASS" else "HOUR PASS"

    val mPrice: String
        get() =//return mPrice;
            "Rp " + mData.price

    override fun getType(): Type {
        return ListItem.Type.PASS;
    }

    init {
        mData = data
    }
}