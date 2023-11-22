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
                    @Query("type") title: String,
                    @Query("index") index: Int,
                    @Query("month") month: Int,
                    @Query("day") day: Int,
                    @Query("hour") hour: Int,
                    @Query("minute") minute: Int,
                    @Query("name") name: String,
    ): Call<Void>

}