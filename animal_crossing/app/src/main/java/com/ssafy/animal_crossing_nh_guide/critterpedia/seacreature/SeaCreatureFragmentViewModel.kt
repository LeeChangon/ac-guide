package com.ssafy.animal_crossing_nh_guide.critterpedia.seacreature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.animal_crossing_nh_guide.database.MyRepository
import com.ssafy.animal_crossing_nh_guide.models.sea_creature.SeaCreature
import com.ssafy.animal_crossing_nh_guide.util.RetrofitUtil
import kotlinx.coroutines.launch
import java.lang.Exception

class SeaCreatureFragmentViewModel: ViewModel() {
    private val _seaCreatureList = MutableLiveData<List<SeaCreature>>(listOf())

    val myRepository = MyRepository.get()

    val seaCreatureList : LiveData<List<SeaCreature>>
        get() = _seaCreatureList

    fun getSeaCreatureList(month:Int) {
        viewModelScope.launch {
            var list:List<SeaCreature>
            try {
                if (month == 0){
                    list = RetrofitUtil.seaCreatureService.getSeaCreatureList()
                }else{
                    list = RetrofitUtil.seaCreatureService.getMonthSeaCreature(month)
                }
            }catch (e: Exception){
                list = listOf()
            }
            _seaCreatureList.value = list
        }
    }
}