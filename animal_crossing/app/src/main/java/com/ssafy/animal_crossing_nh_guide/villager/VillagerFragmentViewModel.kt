package com.ssafy.animal_crossing_nh_guide.villager

import android.util.Log
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

private const val TAG = "VillagerFragmentViewMod_싸피"
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

    fun getSpinnerVillagerList(species: Int, gender:Int){
        Log.d(TAG, "getSpinnerVillagerList: ${species}, ${gender}")
        viewModelScope.launch {
            var list:List<Villager>
            try {
                list = RetrofitUtil.villagerService.getSpinnerVillagerList(species, gender)
            }catch (e: Exception){
                list = listOf()
            }
            _villagerList.value = list
            Log.d(TAG, "getSpinnerVillagerList: ${list}")
        }
    }

    fun getNameVillagerList(name: String){
        viewModelScope.launch {
            var list:List<Villager>
            try {
                list = RetrofitUtil.villagerService.getNameVillagerList(name)
            }catch (e: Exception){
                list = listOf()
            }
            _villagerList.value = list
            Log.d(TAG, "getSpinnerVillagerList: ${list}")
        }
    }

    fun getSpinnerNameVillagerList(species: Int, gender:Int, name: String){
        viewModelScope.launch {
            var list:List<Villager>
            try {
                list = RetrofitUtil.villagerService.getSpinnerNameVillagerList(species, gender, name)
            }catch (e: Exception){
                list = listOf()
            }
            _villagerList.value = list
            Log.d(TAG, "getSpinnerVillagerList: ${list}")
        }
    }

    private val _filePath = MutableLiveData<List<String>>(listOf("", ""))
    val filePath : LiveData<List<String>>
        get() = _filePath
    fun getFilePath(name: String) {
        _filePath.value = listOf("", "")
        viewModelScope.launch {
            var result:List<AcnhVillager>
            try {
                result = RetrofitUtil.acnhService.getAcnhVillager(name, true)
                Log.d(TAG, "getFilePath: ${result}")
            }catch (e: Exception){
                Log.d(TAG, "getFilePath: failed")
                result = listOf()
            }
            if(result.size != 0){
                _filePath.value = listOf(result[0].image_url, result[0].nh_details.house_exterior_url)
                Log.d(TAG, "getFilePath: ${_filePath.value}")
            }
        }
    }

    private val _filePath1 = MutableLiveData<List<String>>(listOf("", ""))
    val filePath1 : LiveData<List<String>>
        get() = _filePath1
    fun getFilePath1(name: String) {
        _filePath1.value = listOf("", "")
        viewModelScope.launch {
            var result:List<AcnhVillager>
            try {
                result = RetrofitUtil.acnhService.getAcnhVillager(name, true)
            }catch (e: Exception){
                result = listOf()
            }
            if(result.size != 0){
                _filePath1.value = listOf(result[0].image_url, result[0].nh_details.house_exterior_url)
            }
        }
    }

    private val _filePath2 = MutableLiveData<List<String>>(listOf("", ""))
    val filePath2 : LiveData<List<String>>
        get() = _filePath2
    fun getFilePath2(name: String) {
        _filePath2.value = listOf("", "")
        viewModelScope.launch {
            var result:List<AcnhVillager>
            try {
                result = RetrofitUtil.acnhService.getAcnhVillager(name, true)
            }catch (e: Exception){
                result = listOf()
            }
            if(result.size != 0){
                _filePath2.value = listOf(result[0].image_url, result[0].nh_details.house_exterior_url)
            }
        }
    }




    var species:Int = 0
    var gender:Int = 0
    var name:String = ""
    var init: Boolean = false
//
//    private val _init = MutableLiveData<Boolean>(false)
//    val init: LiveData<Boolean>
//        get() = _init
}