package com.ssafy.animal_crossing_nh_guide.critterpedia.fish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.animal_crossing_nh_guide.models.fish.Fish
import com.ssafy.animal_crossing_nh_guide.util.RetrofitUtil
import kotlinx.coroutines.launch
import java.lang.Exception

class FishFragmentViewModel: ViewModel() {
    private val _fishList = MutableLiveData<List<Fish>>(listOf())

    val fishList : LiveData<List<Fish>>
        get() = _fishList

    fun getFishList(month:Int) {
        viewModelScope.launch {
            var list:List<Fish>
            try {
                if (month == 0){
                    list = RetrofitUtil.fishService.getFishList()
                }else{
                    list = RetrofitUtil.fishService.getMonthFish(month)
                }
            }catch (e: Exception){
                list = listOf()
            }
            _fishList.value = list
        }
    }
}