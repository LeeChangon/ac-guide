package com.ssafy.animal_crossing_nh_guide.mypage

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.activity.MainActivity
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.config.ApplicationClass
import com.ssafy.animal_crossing_nh_guide.config.BaseFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentMypageBinding
import com.ssafy.animal_crossing_nh_guide.home.HomeBugAdapter
import java.util.Calendar
import java.util.TimeZone

private const val TAG = "MypageFragment_싸피"
class MypageFragment : BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::bind, R.layout.fragment_mypage) {

    private lateinit var mainActivity: MainActivity
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    var timeConfigExpanded = false

    private lateinit var myVillagerAdapter: MyVillagerAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSetting()
        initListener()
    }

    private fun initListener() {
        binding.dateConfigBtn.setOnClickListener {
            timeConfigExpanded = !timeConfigExpanded
            if(!timeConfigExpanded){
                toggleCard(binding.timeCollapseLayout, binding.dateConfigBtn, timeConfigExpanded)
            } else { //펼쳐질 때
                initDate()
                toggleCard(binding.timeCollapseLayout, binding.dateConfigBtn, timeConfigExpanded)
            }
        }

        binding.resetDateBtn.setOnClickListener{
            ApplicationClass.sharedPreferencesUtil.resetTimeDiff()
            refreshTime()
            showToast("현재 시간으로 초기화되었습니다.")
            toggleCard(binding.timeCollapseLayout, binding.dateConfigBtn, !timeConfigExpanded)
        }

        binding.changeConfirmBtn.setOnClickListener {
            val calendar = Calendar.getInstance()

            val year = binding.datePicker.year
            val month = binding.datePicker.month
            val day = binding.datePicker.dayOfMonth
            val hour = binding.timePicker.hour
            val min = binding.timePicker.minute

            calendar.set(year, month, day, hour, min)

            Log.d(TAG, "initListener: 지금 시간 : ${mainActivityViewModel.currentTime.value}")
            Log.d(TAG, "initListener: 선택한 시간 : ${calendar.timeInMillis}")

            ApplicationClass.sharedPreferencesUtil.setTimeDiff(calendar.timeInMillis)
            
            refreshTime()
            toggleCard(binding.timeCollapseLayout, binding.dateConfigBtn, !timeConfigExpanded)

        }

        binding.myAddVillagerBtn.setOnClickListener {
            mainActivity.moveNavItem(R.id.navigation_page_3)
        }

    }


    fun initSetting(){
        initObserve()
        refreshTime()
        initDate()
        initAdapter()

        mainActivityViewModel.getMyVilagerList()
    }

    fun initObserve(){
        mainActivityViewModel.currentTime.observe(viewLifecycleOwner){
            binding.time = mainActivityViewModel.convertLongToTime()
        }

        mainActivityViewModel.myVillagerList.observe(viewLifecycleOwner){
            myVillagerAdapter.list = it
            if(it.isEmpty()){
                binding.myVillagerTitle.visibility = View.GONE
                binding.myVillagerRecyclerview.visibility = View.GONE
                binding.myAddVillagerBtn.visibility = View.VISIBLE
            } else {
                myVillagerAdapter.list = it
                binding.myVillagerTitle.visibility = View.VISIBLE
                binding.myVillagerRecyclerview.visibility = View.VISIBLE
                binding.myAddVillagerBtn.visibility = View.GONE
            }
        }
    }

    fun initDate(){
        val calendar = Calendar.getInstance()
        calendar.time = mainActivityViewModel.getCurrentDate()

        Log.d(TAG, "initDate: ${calendar.time}")

        binding.datePicker.init(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DATE), null)

        binding.timePicker.hour = calendar.get(Calendar.HOUR_OF_DAY)
        binding.timePicker.minute = calendar.get(Calendar.MINUTE)
    }

    fun refreshTime(){
        mainActivityViewModel.setTime()
        mainActivityViewModel.convertLongToTime()
    }

    fun initAdapter(){
        initMyVillagerAdapter()
    }


    //주민 어댑터
    private fun initMyVillagerAdapter(){

        myVillagerAdapter = MyVillagerAdapter(mainActivity, childFragmentManager, mainActivityViewModel)
        myVillagerAdapter.list = listOf()

        val manager = GridLayoutManager(context, 5)

        binding.myVillagerRecyclerview.apply {
            layoutManager = manager
            adapter = myVillagerAdapter
        }
    }
}