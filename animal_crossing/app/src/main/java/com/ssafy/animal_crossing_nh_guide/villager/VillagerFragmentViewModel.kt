package com.ssafy.animal_crossing_nh_guide.villager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.animal_crossing_nh_guide.models.villager.AcnhVillager
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

    private val _filePath = MutableLiveData<String>("")
    val filePath : LiveData<String>
        get() = _filePath
    fun getFilePath(name: String) {
        _filePath.value = ""
        viewModelScope.launch {
            var result:List<AcnhVillager>
            try {
                result = RetrofitUtil.acnhService.getAcnhVillager(name)
            }catch (e: Exception){
                result = listOf()
            }
            if(result.size != 0){
                _filePath.value = result[0].image_url
            }
        }
    }

    private val _filePath1 = MutableLiveData<String>("")
    val filePath1 : LiveData<String>
        get() = _filePath1
    fun getFilePath1(name: String) {
        _filePath1.value = ""
        viewModelScope.launch {
            var result:List<AcnhVillager>
            try {
                result = RetrofitUtil.acnhService.getAcnhVillager(name)
            }catch (e: Exception){
                result = listOf()
            }
            if(result.size != 0){
                _filePath1.value = result[0].image_url
            }
        }
    }

    private val _filePath2 = MutableLiveData<String>("")
    val filePath2 : LiveData<String>
        get() = _filePath2
    fun getFilePath2(name: String) {
        _filePath2.value = ""
        viewModelScope.launch {
            var result:List<AcnhVillager>
            try {
                result = RetrofitUtil.acnhService.getAcnhVillager(name)
            }catch (e: Exception){
                result = listOf()
            }
            if(result.size != 0){
                _filePath2.value = result[0].image_url
            }
        }
    }

}