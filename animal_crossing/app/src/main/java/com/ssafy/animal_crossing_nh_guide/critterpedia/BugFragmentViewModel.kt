package com.ssafy.animal_crossing_nh_guide.critterpedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.animal_crossing_nh_guide.models.bug.Bug
import com.ssafy.animal_crossing_nh_guide.util.RetrofitUtil
import kotlinx.coroutines.launch
import java.lang.Exception

class BugFragmentViewModel: ViewModel() {

    private val _bugList = MutableLiveData<List<Bug>>()

    val bugList : LiveData<List<Bug>>
        get() = _bugList

    fun getBugList() {
        viewModelScope.launch {
            var list:List<Bug>
            try {
                list = RetrofitUtil.bugService.getBugList()
            }catch (e: Exception){
                list = listOf()
            }
            _bugList.value = list
        }
    }
}