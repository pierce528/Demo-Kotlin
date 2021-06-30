package com.pierce.demo.list.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pierce.demo.list.data.PassRepository
import com.pierce.demo.list.room.PassData
import java.lang.Appendable
import java.text.SimpleDateFormat
import java.util.*

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    var mInfo = MutableLiveData<String>()
    var mFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    var mCalendar = Calendar.getInstance()
    val mApp = application

    fun loadData(id: Int) {
        PassRepository.getInstance(mApp).loadData(id, object : PassRepository.Callback2 {

            override fun onDataReady(data: PassData) {
                val sb = StringBuilder()
                sb.append(
                    data.period
                        .toString() + (if (data.type === 0) " DAY PASS" else " HOUR PASS") + "\n"
                )
                sb.append(
                    """
                        Status : ${if (data.status === 0) "Not Activated" else "Activated"}
                        
                        """.trimIndent()
                )
                mCalendar.timeInMillis = data.insertTime
                sb.append(
                    """
                        Insert at: ${mFormat.format(mCalendar.time)}
                        
                        """.trimIndent()
                )
                if (data.status === 1) {
                    mCalendar.timeInMillis = data.startTime
                    val since = mFormat.format(mCalendar.time)
                    mCalendar.timeInMillis = data.endTime
                    val end = mFormat.format(mCalendar.time)
                    sb.append("Activate at : $since\n")
                    sb.append("Expire at : $end\n")
                }
                mInfo.postValue(sb.toString())
            }
        }
        )
    }
}