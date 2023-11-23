package com.ssafy.animal_crossing_nh_guide.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.ssafy.animal_crossing_nh_guide.config.ApplicationClass
import com.ssafy.animal_crossing_nh_guide.util.FirebasePushUtil
import kotlin.math.log

private const val TAG = "MyRepository_싸피"
class MyRepository private constructor(context: Context){
    private val database: MyDatabase = Room.databaseBuilder(
        context.applicationContext,
        MyDatabase::class.java,
        "my_database"
    ).build()

    private val myDao = database.myDao()

    suspend fun getAllCaught() : List<Caught> {
        return myDao.getAllCaught()
    }

    suspend fun getCaught(type : String, index: Int) : Caught{
        return myDao.getCaught(type, index)
    }

    suspend fun insertCaught(caught: Caught){
        Log.d(TAG, "insertCaught: ${caught}")
        myDao.insertCaught(caught)
    }

    suspend fun deleteCaught(caught: Caught){
        var newO = myDao.getCaught(caught.type, caught.index)
        if(newO!=null)
        myDao.deleteCaught(newO)
    }

    suspend fun getAllStar() : List<Star> {
        return myDao.getAllStar()
    }
    suspend fun getStar(type : String, index: Int) : Star{
        Log.d(TAG, "getStar 별 리스트: ${myDao.getStar(type, index)}")
        return myDao.getStar(type, index)
    }

    suspend fun insertStar(star: Star){
        Log.d(TAG, "insertStar: $star")
        myDao.insertStar(star)
    }

    suspend fun deleteStar(star: Star){
        var newStar = myDao.getStar(star.type, star.index)
        if(newStar!=null)
        myDao.deleteStar(newStar)
    }

    suspend fun getAllAlert() : List<Alert> {
        return myDao.getAllAlert()
    }

    suspend fun getAlert(type : String, index: Int) : Alert{
        return myDao.getAlert(type, index)
    }

    suspend fun insertAlert(alert: Alert){


        val monthArr:Array<Int> = alert.month!!.toTypedArray()
        val hour : Array<Int> = alert.time!!.toTypedArray()

        val minute = ApplicationClass.sharedPreferencesUtil.getTimeDiff()[2]
        Log.d(TAG, "insertAlert: 새데이터베이스: ${alert}, 분 : $minute")


        FirebasePushUtil.pushAlarmTo(alert.type, alert.index, monthArr, hour, minute, alert.name)

        myDao.insertAlert(alert)
    }

    suspend fun deleteAlert(alert: Alert){
        var newO = myDao.getAlert(alert.type, alert.index)
        if(newO!=null) {
            FirebasePushUtil.deleteAlarm(newO.type, newO.index)

            myDao.deleteAlert(myDao.getAlert(newO.type, newO.index))
        }
    }
    
    suspend fun getAllMyVillager(): List<MyVillager>{
        return myDao.getAllMyVillager()
    }

    suspend fun getMyVillager(index: Int): MyVillager{
        return myDao.getMyVillager(index)
    }

    suspend fun insertMyVillager(myVillager: MyVillager){
        myDao.insertMyVillager(myVillager)
    }

    suspend fun deleteMyVillager(index:Int){
        myDao.deleteMyVillager(myDao.getMyVillager(index))
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