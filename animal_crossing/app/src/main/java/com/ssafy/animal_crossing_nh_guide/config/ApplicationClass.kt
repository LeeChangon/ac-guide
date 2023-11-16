package com.ssafy.animal_crossing_nh_guide.config

import android.app.Application
import com.ssafy.animal_crossing_nh_guide.util.SharedPreferencesUtil
import retrofit2.Retrofit

class ApplicationClass : Application() {
    companion object{
        lateinit var sharedPreferencesUtil: SharedPreferencesUtil

    }


    override fun onCreate() {
        super.onCreate()

        //shared preference 초기화
        sharedPreferencesUtil = SharedPreferencesUtil(applicationContext)
    }
}