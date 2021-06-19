package com.pierce.demo.list.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(myData: PassData?)

    @Query("INSERT INTO MyTable(type,period,price, insertTime) VALUES(:type,:period,:price, :insertTime)")
    fun insertData(
        type: Int,
        period: Int,
        price: Float,
        insertTime: Long
    )

    @Query("SELECT * FROM MyTable ORDER BY type ASC, status, period")
    fun findAll(): LiveData<List<PassData>>

    @Query("SELECT * FROM MyTable WHERE id = :id")
    fun findDataById(id: Int): PassData

    @Query("SELECT * FROM MyTable WHERE type = :type")
    fun findDataByType(type: Int): LiveData<List<PassData>>

    @Update
    fun updateData(myData: PassData?)

    @Query("UPDATE MyTable SET status = :status, startTime = :startTime, endTime =:endTime WHERE id = :id")
    fun updateData(
        id: Int,
        status: Int,
        startTime: Long,
        endTime: Long
    )

    @Delete
    fun deleteData(myData: PassData?)

    @Query("DELETE  FROM MyTable WHERE id = :id")
    fun deleteData(id: Int)

}