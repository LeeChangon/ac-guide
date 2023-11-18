package com.ssafy.animal_crossing_nh_guide.api

import com.ssafy.animal_crossing_nh_guide.models.sea_creature.SeaCreature
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SeaCreatureService {

    @GET("api/seacreature")
    suspend fun getSeaCreatureList(): List<SeaCreature>

    // {productId}에 해당하는 상품의 정보를 comment와 함께 반환한다.
    // comment 조회시 사용
    @GET("api/seacreature/{no}")
    suspend fun getSeaCreature(@Path("no") no: Int): SeaCreature

    @GET("api/seacreature/month/{month}")
    suspend fun getMonthSeaCreature(@Path("month") month: Int): List<SeaCreature>

    @GET("api/seacreature/month/{month}/{time}")
    suspend fun getMonthTimeSeaCreature(@Path("month") month: Int, @Path("time") time: Int): List<SeaCreature>

}