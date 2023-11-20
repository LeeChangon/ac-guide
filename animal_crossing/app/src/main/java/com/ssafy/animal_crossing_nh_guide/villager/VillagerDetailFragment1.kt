package com.ssafy.animal_crossing_nh_guide.critterpedia.seacreature

import android.os.Bundle
import android.view.View
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.config.BaseFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentVillagerDetail1Binding

class VillagerDetailFragment1(var filepath: String) : BaseFragment<FragmentVillagerDetail1Binding>(
    FragmentVillagerDetail1Binding::bind, R.layout.fragment_villager_detail1
){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.filepath = filepath
        view.cameraDistance = 8000*requireContext().resources.displayMetrics.density
    }
}