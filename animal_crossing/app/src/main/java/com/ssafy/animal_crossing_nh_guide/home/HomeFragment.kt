package com.ssafy.animal_crossing_nh_guide.home

import android.animation.LayoutTransition
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.ssafy.animal_crossing_nh_guide.activity.MainActivity
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.config.ApplicationClass
import com.ssafy.animal_crossing_nh_guide.config.BaseFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentHomeBinding
import com.ssafy.animal_crossing_nh_guide.util.ToggleAnimation


private const val TAG = "HomeFragment_싸피"
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    private lateinit var mainActivity: MainActivity
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    private val checkList = mutableListOf<View>()

//    var bugExpanded = false;
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")

//        binding.homeBugCardview.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

        val imageUrl = "/icons/bugs/agrias_butterfly.png"

        binding.img = imageUrl



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

        // 체크리스트 리스너
        checkList.forEachIndexed { index, it ->
            it.setOnClickListener {
                val flg = ApplicationClass.sharedPreferencesUtil.setCheckList(index)
                toggleCheckList(it, flg)
            }
        }

    }

    fun initChecklist(){
        for(i:Int in 0..7){
            val check = ApplicationClass.sharedPreferencesUtil.getCheckList(i)
            toggleCheckList(checkList[i], check)
        }
    }

    fun toggleCheckList(image : View, check: Boolean){
        if(check){
            image.setBackgroundResource(R.drawable.checklist_checked)
        }
        else {
            image.setBackgroundResource(R.drawable.checklist_unchecked)
        }

//        binding.rock1.setBackgroundResource(R.drawable.checklist_checked)
    }


    fun initSetting(){
        checkList.addAll(arrayOf(binding.fossil1, binding.fossil2, binding.fossil3, binding.fossil4, binding.bottle, binding.moneyTree,
             binding.rock1, binding.rock2, binding.rock3, binding.rock4, binding.rock5, binding.rock6))
        binding.homeToolbar.inflateMenu(R.menu.home_appbar_item)
        refreshTime()
        initChecklist()
    }

    fun refreshTime(){
        mainActivityViewModel.setTime()
        mainActivityViewModel.convertLongToTime()
    }

}