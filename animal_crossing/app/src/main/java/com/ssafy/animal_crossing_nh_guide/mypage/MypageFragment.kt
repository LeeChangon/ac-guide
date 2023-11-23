package com.ssafy.animal_crossing_nh_guide.mypage

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
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
import androidx.databinding.Bindable
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.activity.MainActivity
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.config.ApplicationClass
import com.ssafy.animal_crossing_nh_guide.config.BaseFragment
import com.ssafy.animal_crossing_nh_guide.database.Caught
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentMypageBinding
import com.ssafy.animal_crossing_nh_guide.home.HomeBugAdapter
import com.ssafy.animal_crossing_nh_guide.util.FirebasePushUtil
import com.ssafy.animal_crossing_nh_guide.util.ToggleAnimation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.notify
import java.util.Calendar
import java.util.TimeZone

private const val TAG = "MypageFragment_싸피"
class MypageFragment : BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::bind, R.layout.fragment_mypage) {

//    @get:Bindable
//    var villagerClicked : Boolean = false
//        set(value) {
//            field = value
//            notifyPropertyChanged(BR.clicked)
//        }

    private lateinit var mainActivity: MainActivity
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    var timeConfigExpanded = false

    private lateinit var myVillagerAdapter: MyVillagerAdapter
    private lateinit var myCaughtAdapter1: MyCaughtAdapter
    private lateinit var myCaughtAdapter2: MyCaughtAdapter
    private lateinit var myCaughtAdapter3: MyCaughtAdapter
    private lateinit var myAlertAdapter : MyAlertAdapter
    private lateinit var myStarAdapter : MyStarAdapter

    private lateinit var myCaughtBugList : ArrayList<Caught>
    private lateinit var myCaughtFishList : ArrayList<Caught>
    private lateinit var myCaughtSeaList : ArrayList<Caught>
//    private lateinit var myCaughtSeaList : ArrayList<Alert>



    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSetting()
        initListener()
    }

    fun initSetting(){
        initObserve()
        refreshTime()
        initDate()
        initAdapter()

        initList()
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
            mainActivityViewModel.getAlertList()
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
            mainActivityViewModel.getAlertList()
            refreshTime()
            toggleCard(binding.timeCollapseLayout, binding.dateConfigBtn, !timeConfigExpanded)

        }

        binding.myAddVillagerBtn.setOnClickListener {
            mainActivity.moveNavItem(R.id.navigation_page_3)
        }

        //잡은 생물 접었다 펴기
        binding.bugCaughtHeadLayout.setOnClickListener {
            caughtListAnim(binding.bugCaughtDrawLayout, binding.bugProgressbar, myCaughtBugList)
        }

        binding.fishCaughtHeadLayout.setOnClickListener {
            caughtListAnim(binding.fishCaughtDrawLayout, binding.fishProgressbar, myCaughtFishList)
        }

        binding.seaCaughtHeadLayout.setOnClickListener {
            caughtListAnim(binding.seaCaughtDrawLayout, binding.seaProgressbar, myCaughtSeaList)
        }

    }




    private fun initList() {
        mainActivityViewModel.getMyVilagerList()

        mainActivityViewModel.getStarList()
        mainActivityViewModel.getCaughtList()
        mainActivityViewModel.getAlertList()
    }

    private fun initObserve(){
        mainActivityViewModel.alertList.observe(viewLifecycleOwner){
            it.forEach{
                var month = arrayListOf<Int>()
                it.month!!.forEach{
                    if(it > 12)
                        month.add(it - 12)
                    else
                        month.add(it)
                }
                val monthArr:Array<Int> = month.toTypedArray()
                val hour : Array<Int> = it.time!!.toTypedArray()

                val minute = ApplicationClass.sharedPreferencesUtil.getTimeDiff()[2]
                Log.d(TAG, "insertAlert: 새데이터베이스: ${it}, 분 : $minute")

                FirebasePushUtil.pushAlarmTo(it.type, it.index, monthArr, hour, minute, it.name)
            }
        }

        mainActivityViewModel.currentTime.observe(viewLifecycleOwner){
            binding.time = mainActivityViewModel.convertLongToTime()
        }

        mainActivityViewModel.myVillagerList.observe(viewLifecycleOwner){
            myVillagerAdapter.list = it
            if(it.isEmpty()){
//                binding.myVillagerTitle.visibility = View.GONE
                binding.myVillagerRecyclerview.visibility = View.GONE
                binding.myAddVillagerBtn.visibility = View.VISIBLE
            } else {
                myVillagerAdapter.list = it
//                binding.myVillagerTitle.visibility = View.VISIBLE
                binding.myVillagerRecyclerview.visibility = View.VISIBLE
                binding.myAddVillagerBtn.visibility = View.GONE
            }

//            myVillagerAdapter.notifyDataSetChanged()
        }

        mainActivityViewModel.caughtList.observe(viewLifecycleOwner){
            Log.d(TAG, "initObserve: $it")
            myCaughtBugList = arrayListOf()
            myCaughtFishList = arrayListOf()
            myCaughtSeaList = arrayListOf()

            it.forEach{
                if(it.type == "곤충") myCaughtBugList.add(it)
                else if(it.type == "물고기") myCaughtFishList.add(it)
                else myCaughtSeaList.add(it)
            }

            if(binding.bugCaughtDrawLayout.visibility != View.VISIBLE) {
                ObjectAnimator.ofInt(binding.bugProgressbar, "progress", myCaughtBugList.size)
                    .setDuration(350)
                    .start()
            }
//            binding.bugProgressbar.progress = myCaughtBugList.size
            binding.myCaughtBugProgressTv.text = "${myCaughtBugList.size} / 80"

            if(binding.fishCaughtDrawLayout.visibility != View.VISIBLE) {
                ObjectAnimator.ofInt(binding.fishProgressbar, "progress", myCaughtFishList.size)
                    .setDuration(350)
                    .start()
            }
//            binding.fishProgressbar.progress = myCaughtFishList.size
            binding.myCaughtFishProgressTv.text = "${myCaughtFishList.size} / 80"

            if(binding.seaCaughtDrawLayout.visibility != View.VISIBLE) {
                ObjectAnimator.ofInt(binding.seaProgressbar, "progress", myCaughtSeaList.size)
                    .setDuration(350)
                    .start()
            }
//            binding.seaProgressbar.progress = myCaughtSeaList.size
            binding.myCaughtSeaProgressTv.text = "${myCaughtSeaList.size} / 40"

            myCaughtAdapter1.list = myCaughtBugList
            myCaughtAdapter2.list = myCaughtFishList
            myCaughtAdapter3.list = myCaughtSeaList
            myCaughtAdapter1.notifyDataSetChanged()
            myCaughtAdapter2.notifyDataSetChanged()
            myCaughtAdapter3.notifyDataSetChanged()
            binding.myBugRecyclerview.scheduleLayoutAnimation()
        }

        // 알림 리스트 옵저버
        mainActivityViewModel.alertList.observe(viewLifecycleOwner){
            myAlertAdapter.list = it
            myAlertAdapter.notifyDataSetChanged()
            binding.myAlertRecyclerview.scheduleLayoutAnimation()
            binding.myAlertRecyclerview.scrollToPosition(0);

        }

        //즐겨찾기 옵저버
        mainActivityViewModel.starList.observe(viewLifecycleOwner){
            myStarAdapter.list = it
            myStarAdapter.notifyDataSetChanged()
            binding.myStarRecyclerview.scheduleLayoutAnimation()
            binding.myStarRecyclerview.scrollToPosition(0);

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

    private fun refreshTime(){
        mainActivityViewModel.setTime()
        mainActivityViewModel.convertLongToTime()
    }

    private fun initAdapter(){
        initMyVillagerAdapter()
        initMycaughtAdapter()
        initMyAlertAdapter()
        initMyStarAdapter()
    }


    //주민 어댑터
    private fun initMyVillagerAdapter(){

        myVillagerAdapter = MyVillagerAdapter(mainActivity, mainActivityViewModel)
        myVillagerAdapter.list = listOf()

        val manager = GridLayoutManager(context, 5)

        binding.myVillagerRecyclerview.apply {
            layoutManager = manager
            adapter = myVillagerAdapter
        }
    }

    private fun initMycaughtAdapter(){
        myCaughtAdapter1 = MyCaughtAdapter(mainActivity, mainActivityViewModel)
        myCaughtAdapter1.list = listOf()
        myCaughtAdapter2 = MyCaughtAdapter(mainActivity, mainActivityViewModel)
        myCaughtAdapter2.list = listOf()
        myCaughtAdapter3 = MyCaughtAdapter(mainActivity, mainActivityViewModel)
        myCaughtAdapter3.list = listOf()

        val manager1 = GridLayoutManager(context, 5)
        val manager2 = GridLayoutManager(context, 5)
        val manager3 = GridLayoutManager(context, 5)


        binding.myBugRecyclerview.apply {
            layoutManager = manager1
            adapter = myCaughtAdapter1
        }
        binding.myFishRecyclerview.apply {
            layoutManager = manager2
            adapter = myCaughtAdapter2
        }
        binding.mySeaRecyclerview.apply {
            layoutManager = manager3
            adapter = myCaughtAdapter3
        }
//        binding.myBugRecyclerview.scheduleLayoutAnimation()
    }

    private fun initMyAlertAdapter(){
        myAlertAdapter = MyAlertAdapter(mainActivity, mainActivityViewModel)
        myAlertAdapter.list = listOf()

        val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        manager.reverseLayout = false

        binding.myAlertRecyclerview.apply {
            layoutManager = manager
            adapter = myAlertAdapter
        }
    }

    private fun initMyStarAdapter(){
        myStarAdapter = MyStarAdapter(mainActivity, mainActivityViewModel)
        myStarAdapter.list = listOf()

        val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        manager.reverseLayout = false

        binding.myStarRecyclerview.apply {
            layoutManager = manager
            adapter = myStarAdapter
        }
    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun caughtListAnim(layout: View, progressbar:View, list:List<Caught>){
        Log.d(TAG, "caughtListAnim 접었다펴기: $list")
        if(list.isNotEmpty()) {
            if (layout.visibility == View.VISIBLE) {
                ObjectAnimator.ofInt(progressbar, "progress", list.size)
                    .setDuration(350)
                    .start()
                ToggleAnimation.collapse(layout)
            } else {
                ObjectAnimator.ofInt(progressbar, "progress", 0)
                    .setDuration(350)
                    .start()
                ToggleAnimation.expand(layout)
            }
        }
    }

}