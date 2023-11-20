package com.ssafy.animal_crossing_nh_guide.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import kotlin.math.log

private const val TAG = "MyRepository_싸피"
class MyRepository private constructor(context: Context){
    private val database: MyDatabase = Room.databaseBuilder(
        context.applicationContext,
        MyDatabase::class.java,
        "my_database"
    ).build()

    private val myDao = database.myDao()

    suspend fun getCaught(type : String, index: Int) : Caught{
        Log.d(TAG, "getCaught: ${myDao.getCaught(type, index)}")
        return myDao.getCaught(type, index)
    }

    suspend fun insertCaught(caught: Caught){
        Log.d(TAG, "insertCaught: ${caught}")
        myDao.insertCaught(caught)
    }

    suspend fun deleteCaught(caught: Caught){
        Log.d(TAG, "deleteCaught: ${caught}")
        myDao.deleteCaught(caught)
    }
    suspend fun getStar(type : String, index: Int) : Star{
        Log.d(TAG, "getStar: ${myDao.getStar(type, index)}")
        return myDao.getStar(type, index)
    }

    suspend fun insertStar(Star: Star){
        Log.d(TAG, "insertStar: ${Star}")
        myDao.insertStar(Star)
    }

    suspend fun deleteStar(Star: Star){
        Log.d(TAG, "deleteStar: ${Star}")
        myDao.deleteStar(Star)
    }
    
    suspend fun getAlert(type : String, index: Int) : Alert{
        Log.d(TAG, "getAlert: ${myDao.getAlert(type, index)}")
        return myDao.getAlert(type, index)
    }

    suspend fun insertAlert(Alert: Alert){
        Log.d(TAG, "insertAlert: ${Alert}")
        myDao.insertAlert(Alert)
    }

    suspend fun deleteAlert(Alert: Alert){
        Log.d(TAG, "deleteAlert: ${Alert}")
        myDao.deleteAlert(Alert)
    }
    
    
    
    

    companion object{
        private var INSTANCE : MyRepository ? = null

        fun initialize(context: Context){
            if(INSTANCE == null){
                INSTANCE = MyRepository(context)
            }
        }

        fun get() : MyRepository {
            return INSTANCE ?:
            throw IllegalStateException("MyRepository must be initialized")
        }
    }
}