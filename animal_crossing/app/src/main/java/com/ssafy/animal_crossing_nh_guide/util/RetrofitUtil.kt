package com.ssafy.animal_crossing_nh_guide.util

import com.ssafy.animal_crossing_nh_guide.api.BugService
import com.ssafy.animal_crossing_nh_guide.config.ApplicationClass

class RetrofitUtil {
    companion object{
        val bugService = ApplicationClass.retrofit.create(BugService::class.java)

    }
}
