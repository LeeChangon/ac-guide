package com.ssafy.animal_crossing_nh_guide.activity

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ssafy.animal_crossing_nh_guide.config.ApplicationClass
import java.text.SimpleDateFormat
import java.util.Date

private const val TAG = "MainActivityViewModel_싸피"
class MainActivityViewModel : ViewModel(){
    private var _currentTime = 0L

    val currentTime: Long
        get() = _currentTime

    fun setTime(){
        val time = ApplicationClass.sharedPreferencesUtil.getTime()

        Log.d(TAG, "setTime: $time")
        _currentTime = time
    }

    fun convertLongToTime(time : Long) : String{
        val date = Date(time)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm")

        return format.format(date)
    }
}