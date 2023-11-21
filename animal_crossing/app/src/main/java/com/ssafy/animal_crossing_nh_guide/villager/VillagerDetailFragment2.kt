package com.ssafy.animal_crossing_nh_guide.critterpedia.seacreature

import android.os.Bundle
import android.view.View
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.config.BaseFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentVillagerDetail2Binding
import com.ssafy.animal_crossing_nh_guide.models.villager.Villager


class VillagerDetailFragment2(var filepath: String, var villager: Villager) : BaseFragment<FragmentVillagerDetail2Binding>(
    FragmentVillagerDetail2Binding::bind, R.layout.fragment_villager_detail2
){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.filepath = filepath
        view.cameraDistance = 8000*requireContext().resources.displayMetrics.density
        binding.villager = villager

    }
}