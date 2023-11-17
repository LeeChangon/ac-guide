package com.ssafy.animal_crossing_nh_guide.mypage

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.activity.MainActivity
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.config.BaseFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentMypageBinding
import java.util.Calendar

class MypageFragment : BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::bind, R.layout.fragment_mypage) {

    private lateinit var mainActivity: MainActivity
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    var timeConfigExpanded = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSetting()
        initObserve()
        initListener()
    }

    private fun initListener() {
        binding.dateConfigBtn.setOnClickListener {
            timeConfigExpanded = !timeConfigExpanded
            if(!timeConfigExpanded){
                toggleCard(binding.timeCollapseLayout, binding.dateConfigBtn, timeConfigExpanded)
            } else {
                toggleCard(binding.timeCollapseLayout, binding.dateConfigBtn, timeConfigExpanded)
            }
        }
    }


    fun initSetting(){
        refreshTime()
    }

    fun initObserve(){
        mainActivityViewModel.currentTime.observe(viewLifecycleOwner){
            binding.time = mainActivityViewModel.convertLongToTime()
        }
    }

    fun refreshTime(){
        mainActivityViewModel.setTime()
        mainActivityViewModel.convertLongToTime()
    }
}