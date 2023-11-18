package com.ssafy.animal_crossing_nh_guide.critterpedia.bug

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.animal_crossing_nh_guide.models.bug.Bug
import com.ssafy.animal_crossing_nh_guide.util.RetrofitUtil
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import java.lang.Exception

class BugFragmentViewModel: ViewModel() {

    private val _bugList = MutableLiveData<List<Bug>>()

    val bugList : LiveData<List<Bug>>
        get() = _bugList

    fun getBugList(month:Int) {
        viewModelScope.launch {
            var list:List<Bug>
            try {
                if (month == 0){
                    list = RetrofitUtil.bugService.getBugList()
                }else{
                    list = RetrofitUtil.bugService.getMonthBug(month)
                }
            }catch (e: Exception){
                list = listOf()
            }
            _bugList.value = list
        }
    }
}