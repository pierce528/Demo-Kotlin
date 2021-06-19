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
    var info = MutableLiveData<String>()
    var sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    var cal = Calendar.getInstance()
    val app = application

    fun loadData(id: Int) {
        PassRepository.getInstance(app).loadData(id, object : PassRepository.Callback2 {

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
                cal.timeInMillis = data.insertTime
                sb.append(
                    """
                        Insert at: ${sdf.format(cal.time)}
                        
                        """.trimIndent()
                )
                if (data.status === 1) {
                    cal.timeInMillis = data.startTime
                    val since = sdf.format(cal.time)
                    cal.timeInMillis = data.endTime
                    val end = sdf.format(cal.time)
                    sb.append("Activate at : $since\n")
                    sb.append("Expire at : $end\n")
                }
                info.postValue(sb.toString())
            }
        }
        )
    }
}