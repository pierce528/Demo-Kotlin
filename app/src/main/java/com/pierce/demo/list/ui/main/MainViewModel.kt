package com.pierce.demo.list.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pierce.demo.list.data.PassRepository
//import com.pierce.demo.list.data.URLRepository
import com.pierce.demo.list.data.URLRepository
import com.pierce.demo.list.room.PassData

class MainViewModel(repository : PassRepository, urlRepo: URLRepository) : ViewModel() {
    //URL part
    var mUrlResult = MutableLiveData<String>()
    var mUrlRepository: URLRepository

    //pass list data part
    var mAllPasses: LiveData<List<PassData>>
    var mPassRepository: PassRepository

    init{
        mUrlRepository = urlRepo
        mPassRepository = repository
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
            override fun onSuccess(result: String?) {
                mUrlResult.postValue(result ?: "null")
            }

            override fun onFail(result: String) {
                mUrlResult.postValue(result)
            }
        })
    }
}