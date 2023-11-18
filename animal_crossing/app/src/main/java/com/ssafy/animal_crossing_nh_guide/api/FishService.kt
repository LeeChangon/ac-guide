package com.ssafy.animal_crossing_nh_guide.api

import com.ssafy.animal_crossing_nh_guide.models.fish.Fish
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FishService {

    @GET("api/fish")
    suspend fun getFishList(): List<Fish>

    // {productId}에 해당하는 상품의 정보를 comment와 함께 반환한다.
    // comment 조회시 사용
    @GET("api/fish/{no}")
    suspend fun getFish(@Path("no") no: Int): Fish

    @GET("api/fish/month/{month}")
    suspend fun getMonthFish(@Path("month") month: Int): List<Fish>

    @GET("api/fish/month/{month}/{time}")
    suspend fun getMonthTimeFish(@Path("month") month: Int, @Path("time") time: Int): List<Fish>

}