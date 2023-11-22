package com.ssafy.animal_crossing_nh_guide.util

import android.util.Log
import com.ssafy.animal_crossing_nh_guide.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "FirebasePushUtil_싸피"
class FirebasePushUtil {

    companion object{
        fun getMonthList(list : List<Int>) : List<Int>{
            val monthDiff = ApplicationClass.sharedPreferencesUtil.getTimeDiff()[0]
            val arr = arrayListOf<Int>()

            list.forEach{
                arr.add(it + monthDiff)
            }
            return arr.toList()
        }

        fun getHourList(list : List<Int>) : List<Int>{
            val hourDiff = ApplicationClass.sharedPreferencesUtil.getTimeDiff()[1]

            var i = list[0]
            val arr = arrayListOf<Int>()

            list.forEach{

                if(!(i == 23 && it == 0) && i - it != -1) {
                    var result = it + hourDiff
                    Log.d(TAG, "getHourList: 시간$it, 차이$hourDiff")

                    
                    arr.add(result)
                }
                i = it
            }

            Log.d(TAG, "getHourList: $arr")

            return arr.toList()
        }

        fun deleteAlarm(type: String, index: Int){
            RetrofitUtil.firebaseTokenService.deleteAlarmTo(ApplicationClass.token, type, index).enqueue(object :
            Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if(response.isSuccessful){
                        val res = response.body()
                        Log.d(TAG, "onResponse: $res")
                    } else {
                        Log.d(TAG, "onResponse: Error Code ${response.code()}")
                    }
                }
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d(TAG, "onFailure: t.message ?: \"토큰 정보 등록 중 통신오류\"")
                }
            })

        }

        fun pushAlarmTo(type:String, index:Int, month:Array<Int>, hour:Array<Int>, minute : Int, name: String){
            // 새로운 토큰 수신 시 서버로 전송
//            val storeService = ApplicationClass.retrofit.create(FirebaseTokenService::class.java)
            RetrofitUtil.firebaseTokenService.pushAlarmTo(ApplicationClass.token, type, index, month, hour, minute, name).enqueue(object :
                Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if(response.isSuccessful){
                        val res = response.body()
                        Log.d(TAG, "onResponse: $res")
//                        Log.d(com.ssafy.animal_crossing_nh_guide.activity.TAG, "onResponse: $res")
                    } else {
                        Log.d(TAG, "onResponse: Error Code ${response.code()}")
//                        Log.d(com.ssafy.animal_crossing_nh_guide.activity.TAG, "onResponse: Error Code ${response.code()}")
                    }
                }
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d(TAG, "onFailure: t.message ?: \"토큰 정보 등록 중 통신오류\"")
//                    Log.d(com.ssafy.animal_crossing_nh_guide.activity.TAG, t.message ?: "토큰 정보 등록 중 통신오류")
                }
            })
        }

    }
}