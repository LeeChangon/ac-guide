package com.ssafy.animal_crossing_nh_guide.critterpedia.fish

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.activity.MainActivity
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.config.BaseFragment
import com.ssafy.animal_crossing_nh_guide.critterpedia.ViewPagerAdapter
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentFishBinding

class FishFragment : BaseFragment<FragmentFishBinding>(
    FragmentFishBinding::bind,
    R.layout.fragment_fish
) {

    private var tabTextList = mutableListOf<String>()
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    private lateinit var mainActivity: MainActivity
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
        tabTextList.add("전체")
        for(i in 1..12){
            tabTextList.add("${i}월")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPagerAdapter = ViewPagerAdapter(this, "Fish")
        binding.fishViewPager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.fishTabLayout, binding.fishViewPager) { tab, position ->
            tab.text = tabTextList[position]
        }.attach()
    }


}