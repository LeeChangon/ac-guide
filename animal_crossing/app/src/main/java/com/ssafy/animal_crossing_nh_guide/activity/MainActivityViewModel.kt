package com.ssafy.animal_crossing_nh_guide.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.animal_crossing_nh_guide.config.ApplicationClass
import com.ssafy.animal_crossing_nh_guide.models.bug.Bug
import com.ssafy.animal_crossing_nh_guide.models.fish.Fish
import com.ssafy.animal_crossing_nh_guide.models.sea_creature.SeaCreature
import com.ssafy.animal_crossing_nh_guide.util.RetrofitUtil
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

private const val TAG = "MainActivityViewModel_싸피"
class MainActivityViewModel : ViewModel(){
    private val _currentTime = MutableLiveData<Long>()

    val currentTime : LiveData<Long>
        get() = _currentTime

    fun setTime(){
        val time = ApplicationClass.sharedPreferencesUtil.getTime()

        Log.d(TAG, "setTime: $time")
        _currentTime.value = time
    }

    fun getCurrentDate(): Date{

        return Date(currentTime.value!!)
    }

    fun convertLongToTime(): String{
        val date = Date(_currentTime.value!!)
        val format = SimpleDateFormat("yyyy.MM.dd EEE HH:mm")

        Log.d(TAG, "convertLongToTime: 계산된 시간 : ${format.format(date)}")
        return format.format(date)
    }

    //토글 라이브 데이터
    private val _bugExpanded = MutableLiveData(false)

    val bugExpanded : LiveData<Boolean>
        get() = _bugExpanded

    fun toggleBug(){
        _bugExpanded.value = !_bugExpanded.value!!
    }

    private val _fishExpanded = MutableLiveData(false)

    val fishExpanded : LiveData<Boolean>
        get() = _fishExpanded

    fun toggleFish(){
        _fishExpanded.value = !_fishExpanded.value!!
    }

    private val _critterExpanded = MutableLiveData(false)

    val critterExpanded : LiveData<Boolean>
        get() = _critterExpanded

    fun toggleCritter(){
        _critterExpanded.value = !_critterExpanded.value!!
    }

    private val _checkListExpanded = MutableLiveData(false)

    val checkListExpanded : LiveData<Boolean>
        get() = _checkListExpanded

    fun toggleCheckList(){
        _checkListExpanded.value = !_checkListExpanded.value!!
    }

    // 지금 잡히는 리스트
    //벌레
    private val _nowBugList= MutableLiveData<List<Bug>>()

    val nowBugList : LiveData<List<Bug>>
        get() = _nowBugList


    fun getNowBugList(){
        val calendar = Calendar.getInstance()
        calendar.time = getCurrentDate()

        val month = calendar.get(Calendar.MONTH) + 1
        val hour = calendar.get(Calendar.HOUR)

        viewModelScope.launch {
            var list:List<Bug>

            try {
                list = RetrofitUtil.bugService.getMonthTimeBug(month, hour)

            }catch (e: Exception){
                list = listOf()
            }

            Log.d(TAG, "getNowBugList: ${list.size}")
            _nowBugList.value = list
        }
    }

    //물고기
    private val _nowFishList= MutableLiveData<List<Fish>>()

    val nowFishList : LiveData<List<Fish>>
        get() = _nowFishList

    fun getNowFishList(){
        val calendar = Calendar.getInstance()
        calendar.time = getCurrentDate()

        val month = calendar.get(Calendar.MONTH) + 1
        val hour = calendar.get(Calendar.HOUR)

        viewModelScope.launch {
            var list:List<Fish>

            try {
                list = RetrofitUtil.fishService.getMonthTimeFish(month, hour)

            }catch (e: Exception){
                list = listOf()
            }

            Log.d(TAG, "getNowFishList: ${list.size}")
            _nowFishList.value = list
        }
    }

    //해산물
    private val _nowSeaList= MutableLiveData<List<SeaCreature>>()

    val nowSeaList : LiveData<List<SeaCreature>>
        get() = _nowSeaList

    fun getNowSeaList(){
        val calendar = Calendar.getInstance()
        calendar.time = getCurrentDate()

        val month = calendar.get(Calendar.MONTH) + 1
        val hour = calendar.get(Calendar.HOUR)

        viewModelScope.launch {
            var list:List<SeaCreature>

            try {
                list = RetrofitUtil.seaCreatureService.getMonthTimeSeaCreature(month, hour)

            }catch (e: Exception){
                Log.d(TAG, "getNowSeaList: 오류")
                list = listOf()
            }

            Log.d(TAG, "getNowSeaList: ${list.size}")
            _nowSeaList.value = list
        }
    }
}