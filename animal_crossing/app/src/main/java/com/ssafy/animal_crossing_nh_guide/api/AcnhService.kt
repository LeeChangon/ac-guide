package com.ssafy.animal_crossing_nh_guide.api

import com.ssafy.animal_crossing_nh_guide.models.villager.AcnhVillager
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface AcnhService {
    @Headers(
        "x-api-key: 02abb6f5-74ad-427c-8dbe-214d844a82b0",
        "Accept-Version: 1.0.5"
    )
    @GET("villagers")
    suspend fun getAcnhAllVillager(): List<AcnhVillager>

    @Headers(
        "x-api-key: 02abb6f5-74ad-427c-8dbe-214d844a82b0",
        "Accept-Version: 1.0.5"
    )
    @GET("villagers?")
    suspend fun getAcnhVillager(@Query("name") name: String, @Query("nhdetails") nhdetails: Boolean): List<AcnhVillager>
}