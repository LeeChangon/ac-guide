package com.ssafy.animal_crossing_nh_guide.api

import com.ssafy.animal_crossing_nh_guide.models.EventData
import com.ssafy.animal_crossing_nh_guide.models.bug.Bug
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventService {


    @GET("api/event/{time}")
    suspend fun getEvent(@Path("time") time: Long): EventData

}