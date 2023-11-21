package com.ssafy.animal_crossing_nh_guide.critterpedia.seacreature

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import com.google.android.material.animation.AnimationUtils
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.config.BaseFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentVillagerDetail1Binding
import com.ssafy.animal_crossing_nh_guide.models.villager.Villager

class VillagerDetailFragment1(var filepath: String, var villager: Villager) : BaseFragment<FragmentVillagerDetail1Binding>(
    FragmentVillagerDetail1Binding::bind, R.layout.fragment_villager_detail1
){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.filepath = filepath
        view.cameraDistance = 8000*requireContext().resources.displayMetrics.density
        binding.villager = villager
        val anim = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.left_right)
        binding.shine.startAnimation(anim)
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                binding.shine.visibility = View.GONE
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })

    }
}