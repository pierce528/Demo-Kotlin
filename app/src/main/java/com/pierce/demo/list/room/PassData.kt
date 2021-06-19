package com.pierce.demo.list.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MyTable")
data class PassData(var type: Int, var period: Int, var price: Float, var insertTime: Long) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    //pass activation related
    var status = 0
    var startTime: Long = 0
    var endTime: Long = 0

}