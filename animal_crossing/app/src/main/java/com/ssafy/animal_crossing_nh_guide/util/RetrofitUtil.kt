package com.ssafy.animal_crossing_nh_guide.util

import android.app.Application
import com.ssafy.animal_crossing_nh_guide.api.AcnhService
import com.ssafy.animal_crossing_nh_guide.api.BugService
import com.ssafy.animal_crossing_nh_guide.api.FishService
import com.ssafy.animal_crossing_nh_guide.api.SeaCreatureService
import com.ssafy.animal_crossing_nh_guide.api.VillagerService
import com.ssafy.animal_crossing_nh_guide.config.ApplicationClass

class RetrofitUtil {
    companion object{
        val bugService = ApplicationClass.retrofit.create(BugService::class.java)
        val fishService = ApplicationClass.retrofit.create(FishService::class.java)
        val seaCreatureService = ApplicationClass.retrofit.create(SeaCreatureService::class.java)
        val villagerService = ApplicationClass.retrofit.create(VillagerService::class.java)
        val acnhService = ApplicationClass.retrofitacnh.create(AcnhService::class.java)
    }
}
