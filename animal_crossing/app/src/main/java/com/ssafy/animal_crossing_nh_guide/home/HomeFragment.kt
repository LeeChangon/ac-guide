package com.ssafy.animal_crossing_nh_guide.home

import android.animation.LayoutTransition
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.ssafy.animal_crossing_nh_guide.activity.MainActivity
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.config.BaseFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentHomeBinding
import com.ssafy.animal_crossing_nh_guide.util.ToggleAnimation


private const val TAG = "HomeFragment_싸피"
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    private lateinit var mainActivity: MainActivity
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

//    var bugExpanded = false;
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")

//        binding.homeBugCardview.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

        initSetting()
        initListener()
        initObserve()
    }

    private fun initObserve() {
        mainActivityViewModel.currentTime.observe(viewLifecycleOwner){
            binding.time = mainActivityViewModel.convertLongToTime()
        }

        mainActivityViewModel.bugExpanded.observe(viewLifecycleOwner){
            toggleCard(binding.homeBugDrawLayout, binding.homeBugDrawIcon, mainActivityViewModel.bugExpanded.value!!)
        }

        mainActivityViewModel.fishExpanded.observe(viewLifecycleOwner){
            toggleCard(binding.homeFishDrawLayout, binding.homeFishDrawIcon, mainActivityViewModel.fishExpanded.value!!)
        }

        mainActivityViewModel.critterExpanded.observe(viewLifecycleOwner){
            toggleCard(binding.homeCritterDrawLayout, binding.homeCritterDrawIcon, mainActivityViewModel.critterExpanded.value!!)
        }
    }

    private fun initListener() {
        //새로고침 버튼 이벤트
        binding.homeToolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.time_refresh_btn -> {
                    refreshTime()
                    true
                }
                else -> false
            }
        }

        binding.dateTv.setOnClickListener {
            //다이얼로그 띄우기로 시간 설정
        }

        binding.homeBugHeadLayout.setOnClickListener {
            mainActivityViewModel.toggleBug()
        }

        binding.homeFishHeadLayout.setOnClickListener {
            mainActivityViewModel.toggleFish()
        }

        binding.homeCritterHeadLayout.setOnClickListener {
            mainActivityViewModel.toggleCritter()
        }

    }




    fun initSetting(){
        binding.homeToolbar.inflateMenu(R.menu.home_appbar_item)
        refreshTime()
    }

    fun refreshTime(){
        mainActivityViewModel.setTime()
        mainActivityViewModel.convertLongToTime()
    }

}