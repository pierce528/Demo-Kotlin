package com.pierce.demo.list.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PassData::class], version = 1)
abstract class DataBase : RoomDatabase() {

    companion object {
        const val DB_NAME = "PassData.db"

        @Volatile
        private var INSTANCE: DataBase? = null

        @Synchronized
        fun getInstance(context: Context): DataBase {
            return INSTANCE ?: Room.databaseBuilder(context, DataBase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build().also { INSTANCE = it }
        }
    }

    abstract fun dataDao() : DataDao
}