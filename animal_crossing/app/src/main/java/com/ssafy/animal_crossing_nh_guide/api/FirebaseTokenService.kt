package com.ssafy.animal_crossing_nh_guide.api
import retrofit2.Call
import retrofit2.http.*

interface FirebaseTokenService {
    // Token정보 서버로 전송
    @POST("token")
    fun uploadToken(@Query("token") token: String): Call<String>

    @POST("broadcast")
    fun broadcast(@Query("title") title: String, @Query("body") body: String): Call<Integer>

    @POST("pushAlarmTo")
    fun pushAlarmTo(@Query("token") token: String,
                    @Query("type") type: String,
                    @Query("index") index: Int,
                    @Query("month") month: Array<Int>,
                    @Query("hour") hour: Array<Int>,
                    @Query("name") name: String,
    ): Call<Void>

    @DELETE("deleteAlarmTo")
    fun deleteAlarmTo(@Query("token") token: String,
                    @Query("type") type: String,
                    @Query("index") index: Int,
    ): Call<Void>
}