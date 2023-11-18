package com.ssafy.animal_crossing_nh_guide.api

import com.ssafy.animal_crossing_nh_guide.models.bug.Bug
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BugService {

    @GET("api/bug")
    suspend fun getBugList(): List<Bug>

    // {productId}에 해당하는 상품의 정보를 comment와 함께 반환한다.
    // comment 조회시 사용
    @GET("api/bug/{no}")
    suspend fun getBug(@Path("no") no: Int): Bug

    @GET("api/bug/month/{month}")
    suspend fun getMonthBug(@Path("month") month: Int): List<Bug>

    @GET("api/bug/month/{month}/{time}")
    suspend fun getMonthTimeBug(@Path("month") month: Int, @Path("time") time: Int): List<Bug>

}