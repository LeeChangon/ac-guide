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


private const val TAG = "HomeFragment_싸피"
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    private lateinit var mainActivity: MainActivity
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    var bugExpanded = false;
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
            bugExpanded = !bugExpanded
            if(bugExpanded){
                binding.homeBugDrawLayout.visibility = View.VISIBLE

                binding.homeBugDrawBtn.animate().setDuration(500).rotation(180f)
            }
            else{
                binding.homeBugDrawLayout.visibility = View.GONE
                binding.homeBugDrawBtn.animate().setDuration(500).rotation(0f)
            }
        }
    }


    fun initSetting(){

        binding.homeToolbar.inflateMenu(R.menu.home_appbar_item)
        refreshTime()

    }

    fun refreshTime(){
        mainActivityViewModel.setTime()
        mainActivityViewModel.convertLongToTime()
        binding.dateTv.text = mainActivityViewModel.currentTime.value
    }

}