package com.ssafy.animal_crossing_nh_guide.api

import com.ssafy.animal_crossing_nh_guide.models.villager.Villager
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface VillagerService {

    @GET("api/villager")
    suspend fun getVillagerList(): List<Villager>

    // {productId}에 해당하는 상품의 정보를 comment와 함께 반환한다.
    // comment 조회시 사용
    @GET("api/villager/{no}")
    suspend fun getVillager(@Path("no") no: Int): Villager

    @GET("api/villager/species/{species}")
    suspend fun getSpeciesVillagerList(@Path("species") species: Int): List<Villager>

}