package com.pierce.demo.list.ui.main

import com.pierce.demo.list.room.PassData

class PassItem(data: PassData) : ListItem() {

    var mData: PassData

    val passName: String
        get() =//return mPassName;
            mData.period
                .toString() + " " + if (mData.type === 0) "DAY PASS" else "HOUR PASS"

    val price: String
        get() =//return mPrice;
            "Rp " + mData.price

    override fun getType(): Type {
        return ListItem.Type.PASS;
    }

    init {
        mData = data
    }
}