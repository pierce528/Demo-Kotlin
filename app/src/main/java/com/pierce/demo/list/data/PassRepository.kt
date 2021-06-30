package com.pierce.demo.list.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.pierce.demo.list.App
import com.pierce.demo.list.room.DataBase
import com.pierce.demo.list.room.DataDao
import com.pierce.demo.list.room.PassData
import java.util.*

/**
 * This class is a interface for ViewModel to perform data manipulation
 * by interacting with the database to get/update pass data.
 * Following the AAC's architecture.
 */
class PassRepository private constructor(application : Application) {
    private val mAllPasses: LiveData<List<PassData>>
    private val mPassDao: DataDao

    fun getAllPasses() : LiveData<List<PassData>>{
        return mAllPasses
    }

    fun insert(type: Int, amount: Int) {
        Thread(Runnable {
            val data =
                PassData(type, amount, 1.25f, Calendar.getInstance().timeInMillis)
            mPassDao.insertData(data)
        }).start()
    }

    fun update(data: PassData) {
        val cal = Calendar.getInstance()
        val now = cal.timeInMillis
        cal.add(
            if (data.type === 0) Calendar.DAY_OF_MONTH else Calendar.HOUR,
            data.period
        )
        val expire = cal.timeInMillis
        Thread(Runnable {
            mPassDao
                .updateData(data.id, 1, now, expire)
        }).start()
    }

    fun loadData(id: Int, callback: Callback2) {
        Thread(Runnable {
            val data: PassData =
                    mPassDao.findDataById(id)
            callback.onDataReady(data)
        }).start()
    }

    interface Callback2 {
        fun onDataReady(data: PassData)
    }

    companion object {
        @Volatile
        private var INSTANCE: PassRepository? = null

        fun getInstance(application: Application) : PassRepository {
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: PassRepository(application).also { INSTANCE = it }
            }
        }
    }

    init {
        mPassDao = DataBase.getInstance(application.applicationContext).dataDao()
        mAllPasses = mPassDao.findAll()
    }
}