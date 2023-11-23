package com.ssafy.animal_crossing_nh_guide.critterpedia.seacreature

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.config.BaseFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentVillagerDetail2Binding
import com.ssafy.animal_crossing_nh_guide.models.villager.Villager
import com.ssafy.animal_crossing_nh_guide.villager.VillagerFragmentViewModel

private const val TAG = "VillagerDetailFragment2_싸피"
class VillagerDetailFragment2(var filepath: List<String>, var villager: Villager) : BaseFragment<FragmentVillagerDetail2Binding>(
    FragmentVillagerDetail2Binding::bind, R.layout.fragment_villager_detail2
){

    val villagerFragmentViewModel: VillagerFragmentViewModel by viewModels({ requireParentFragment() })
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.filepath = filepath[1]
        view.cameraDistance = 8000*requireContext().resources.displayMetrics.density
        val birthday = villager.birthday.split("*", "/")
        binding.bmonth = birthday[1]
        binding.bday = birthday[0]
        Log.d(TAG, "onViewCreated: ${birthday}")
        binding.villager = villager

    }
}