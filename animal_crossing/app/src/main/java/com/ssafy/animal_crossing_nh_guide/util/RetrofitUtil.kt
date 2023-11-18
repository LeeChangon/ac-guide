package com.ssafy.animal_crossing_nh_guide.util

import com.ssafy.animal_crossing_nh_guide.api.BugService
import com.ssafy.animal_crossing_nh_guide.api.FishService
import com.ssafy.animal_crossing_nh_guide.api.SeaCreatureService
import com.ssafy.animal_crossing_nh_guide.config.ApplicationClass

class RetrofitUtil {
    companion object{
        val bugService = ApplicationClass.retrofit.create(BugService::class.java)
        val fishService = ApplicationClass.retrofit.create(FishService::class.java)
        val seaCreatureService = ApplicationClass.retrofit.create(SeaCreatureService::class.java)

    }
}
