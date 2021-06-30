package com.pierce.demo.list.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.pierce.demo.list.data.PassRepository


class AddViewModel(application: Application) : AndroidViewModel(application) {
    var mApp : Application = application

    fun addNewPass(type: Int, amount: Int) {
        PassRepository.getInstance(mApp).insert(type, amount)
    }
}
