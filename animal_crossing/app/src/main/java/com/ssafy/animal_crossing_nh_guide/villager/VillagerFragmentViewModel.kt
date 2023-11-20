package com.ssafy.animal_crossing_nh_guide.villager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.animal_crossing_nh_guide.models.villager.Villager
import com.ssafy.animal_crossing_nh_guide.util.RetrofitUtil
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import java.lang.Exception

class VillagerFragmentViewModel: ViewModel() {

    private val _villagerList = MutableLiveData<List<Villager>>(listOf())

    val villagerList : LiveData<List<Villager>>
        get() = _villagerList

    fun getVillagerList() {
        viewModelScope.launch {
            var list:List<Villager>
            try {
                list = RetrofitUtil.villagerService.getVillagerList()
            }catch (e: Exception){
                list = listOf()
            }
            _villagerList.value = list
        }
    }

    fun getSpeciesVillagerList(species: Int){
        viewModelScope.launch {
            var list:List<Villager>
            try {
                list = RetrofitUtil.villagerService.getSpeciesVillagerList(species)
            }catch (e: Exception){
                list = listOf()
            }
            _villagerList.value = list
        }
    }
}