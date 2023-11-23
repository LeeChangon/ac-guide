package com.ssafy.animal_crossing_nh_guide.critterpedia.seacreature

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import com.google.android.material.animation.AnimationUtils
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.config.BaseFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentVillagerDetail1Binding
import com.ssafy.animal_crossing_nh_guide.models.villager.Villager
import com.ssafy.animal_crossing_nh_guide.villager.VillagerFragmentViewModel

private const val TAG = "VillagerDetailFragment1_싸피"
class VillagerDetailFragment1(val villagerFragmentViewModel: VillagerFragmentViewModel, var currentPage: Int) : BaseFragment<FragmentVillagerDetail1Binding>(
    FragmentVillagerDetail1Binding::bind, R.layout.fragment_villager_detail1
){

    override fun onAttach(context: Context) {
        super.onAttach(context)
        villagerFragmentViewModel.getFilePath(villagerFragmentViewModel.villagerList.value!![currentPage].name.name_USen)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.cameraDistance = 8000*requireContext().resources.displayMetrics.density

        binding.filepath = ""

        villagerFragmentViewModel.filePath.observe(viewLifecycleOwner){
            binding.filepath = it[0]
            if(it[0] != ""){
                binding.villager = villagerFragmentViewModel.villagerList.value!![currentPage]
            }
            Log.d(TAG, "onViewCreated: ${it[0]}, ${villagerFragmentViewModel.villagerList.value!![currentPage].name.name_KRko}")
        }



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