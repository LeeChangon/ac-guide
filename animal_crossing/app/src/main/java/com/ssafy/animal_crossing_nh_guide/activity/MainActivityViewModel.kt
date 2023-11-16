package com.ssafy.animal_crossing_nh_guide.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.animal_crossing_nh_guide.config.ApplicationClass
import java.text.SimpleDateFormat
import java.util.Date

private const val TAG = "MainActivityViewModel_싸피"
class MainActivityViewModel : ViewModel(){
    private val _currentTime = MutableLiveData<Long>()

    val currentTime = MutableLiveData<String>()
//        get() = _currentTime

    fun setTime(){
        val time = ApplicationClass.sharedPreferencesUtil.getTime()

        Log.d(TAG, "setTime: $time")
        _currentTime.value = time
    }

    fun convertLongToTime(){
        val date = Date(_currentTime.value!!)
        val format = SimpleDateFormat("yyyy.MM.dd EEE HH:mm")

        currentTime.value = format.format(date)
    }
}