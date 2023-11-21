package com.ssafy.animal_crossing_nh_guide.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Caught::class, Star::class, Alert::class, MyVillager::class], version = 1)
abstract class MyDatabase : RoomDatabase(){
    abstract fun myDao() : MyDao
}