package com.pierce.demo.list.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pierce.demo.list.data.PassRepository
import com.pierce.demo.list.data.URLRepository
import com.pierce.demo.list.room.PassData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    //URL part
    var mUrlResult = MutableLiveData<String>()
    var mUrlRepository: URLRepository = URLRepository()

    //pass list data part
    lateinit var mAllPasses: LiveData<List<PassData>>
    lateinit var mPassRepository: PassRepository


    init{
        mPassRepository = PassRepository.getInstance(application)
        mAllPasses = mPassRepository.getAllPasses()
    }

    fun getAllPasses(): LiveData<List<PassData>> {
        return mAllPasses
    }

    fun insert(type: Int, amount: Int) {
        mPassRepository.insert(type, amount)
    }

    fun update(data: PassData) {
        mPassRepository.update(data)
    }

    fun getUrlResult() {
        mUrlRepository.getUrlContent(object : URLRepository.RequestCallback {
            override fun onSuccess(result: String) {
                mUrlResult.postValue(result)
            }

            override fun onFail(result: String) {
                mUrlResult.postValue(result)
            }
        })
    }
}