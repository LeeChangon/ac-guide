package com.ssafy.animal_crossing_nh_guide.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import java.util.Date

@TypeConverters(DateListConverters::class)
@Database(entities = [Caught::class, Star::class, Alert::class, MyVillager::class], version = 1)
abstract class MyDatabase : RoomDatabase(){
    abstract fun myDao() : MyDao
}

class DateListConverters {
    @TypeConverter
    fun listToJson(value: List<Int>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Int>? {
        return Gson().fromJson(value,Array<Int>::class.java)?.toList()
    }
}
