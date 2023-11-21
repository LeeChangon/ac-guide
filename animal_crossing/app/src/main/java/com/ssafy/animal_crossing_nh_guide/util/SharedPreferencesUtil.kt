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

        Log.d(TAG, "setTimeDiff: ${preferences.getLong("timeDiff", 0)}")
    }

    fun resetTimeDiff(){
        val editor = preferences.edit()
        editor.putLong("timeDiff", 0)
        editor.apply()
    }

    fun getTime(): Long{
        val timeDiff = preferences.getLong("timeDiff", 0)

        Log.d(TAG, "getTime: ${timeDiff}")
        Log.d(TAG, "sp getTime: ${System.currentTimeMillis() + timeDiff}")

        return System.currentTimeMillis() + timeDiff
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