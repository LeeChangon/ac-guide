package com.ssafy.animal_crossing_nh_guide.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.Calendar
import java.util.Date

private const val TAG = "SharedPreferencesUtil_싸피"
class SharedPreferencesUtil (context: Context) {
    val SHARED_PREFERENCES_NAME = "acnh_preference"
//    val COOKIES_KEY_NAME = "cookies"

    var preferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun setTimeDiff(date : Long){
        val editor = preferences.edit()
        val diff = date - System.currentTimeMillis()
//        Duration.between(System.currentTimeMillis(), date).toMillis()

        editor.putLong("timeDiff", diff)
        editor.apply()
        Log.d(TAG, "setTimeDiff: ${diff}")
    }

    fun resetTimeDiff(){
        val editor = preferences.edit()
        editor.putLong("timeDiff", 0)
        editor.apply()
    }

    fun getTimeDiff() : List<Int>{
        val myTime = preferences.getLong("timeNow", System.currentTimeMillis())
        val realTime = System.currentTimeMillis()
        
        val calendar = Calendar.getInstance()
        calendar.time = Date(myTime)

        val myMonth = calendar.get(Calendar.MONTH)
        val myHour = calendar.get(Calendar.HOUR_OF_DAY)
        val myMinute = calendar.get(Calendar.MINUTE)
//        Log.d(TAG, "지금 시간: ${nowDay}일 ${nowHour}시")

        calendar.time = Date(realTime)

        val realMonth = calendar.get(Calendar.MONTH)
        val realHour = calendar.get(Calendar.HOUR_OF_DAY)
        val realMinute = calendar.get(Calendar.MINUTE)
//        Log.d(TAG, "새로운 시간: ${newDay}일 ${newHour}시")

        val monthDiff = realMonth - myMonth
        var hourDiff =  realHour - myHour
        val minuteDiff = realMinute - myMinute

        var minuteResult = 0
        if(minuteDiff < 0 ) {
            minuteResult = 60 + minuteDiff
            hourDiff -= 1
        }
        else minuteResult = 0 + minuteDiff

        Log.d(TAG, "getTimeDiff: 달 : ${monthDiff}, 시간 : ${hourDiff}, 분 : $minuteResult")

        return listOf(monthDiff, hourDiff, minuteResult)
    }


    fun getTime(): Long{
        val timeDiff = preferences.getLong("timeDiff", 0)

        val timeNow = System.currentTimeMillis() + timeDiff

        timeCheck(timeNow)

        val editor = preferences.edit()
        editor.putLong("timeNow", timeNow)
        editor.apply()

        return timeNow
    }

    fun timeCheck(new : Long){
        val now = preferences.getLong("timeNow", System.currentTimeMillis())

        if(new < now) resetCheckList()
        else{
            val calendar = Calendar.getInstance()
            calendar.time = Date(now)

            val nowDay = calendar.get(Calendar.DATE)
            val nowHour = calendar.get(Calendar.HOUR_OF_DAY)
            Log.d(TAG, "지금 시간: ${nowDay}일 ${nowHour}시")

            calendar.time = Date(new)

            val newDay = calendar.get(Calendar.DATE)
            val newHour = calendar.get(Calendar.HOUR_OF_DAY)
            Log.d(TAG, "새로운 시간: ${newDay}일 ${newHour}시")

            if(newHour >= 5){
                if(nowDay < newDay) resetCheckList()
                else if(nowDay == newDay && nowHour < 5) resetCheckList()
            }
        }

//        nowDate
    }

    fun resetCheckList(){
        val editor = preferences.edit()
        for(i: Int in 0..21){
            editor.putBoolean("check$i", false)
        }

        editor.apply()
    }

    //0~11 기타 체크리스트 12~21 주민 체크리스트
    fun getCheckList(idx : Int) : Boolean{
        return preferences.getBoolean("check$idx", false)
    }

    fun setCheckListWithValue(idx: Int, flg: Boolean){
        val editor = preferences.edit()
        editor.putBoolean("check$idx", flg)
        editor.apply()
    }
    fun setCheckList(idx : Int) : Boolean{
        val editor = preferences.edit()
        val flg = !preferences.getBoolean("check$idx", false)
        editor.putBoolean("check$idx", flg)
        editor.apply()

        return flg
    }

}