package com.ssafy.animal_crossing_nh_guide.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.ssafy.animal_crossing_nh_guide.activity.MainActivity
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.config.ApplicationClass
import com.ssafy.animal_crossing_nh_guide.config.BaseFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentHomeBinding
import com.ssafy.animal_crossing_nh_guide.villager.VillagerFragment


private const val TAG = "HomeFragment_싸피"
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    private lateinit var mainActivity: MainActivity
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    private val checkList = mutableListOf<View>()

    private lateinit var homeBugAdapter: HomeBugAdapter
    private lateinit var homeFishAdapter: HomeFishAdapter
    private lateinit var homeSeaAdapter: HomeSeaAdapter


//    var bugExpanded = false;
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")




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

        mainActivityViewModel.checkListExpanded.observe(viewLifecycleOwner){
            toggleCard(binding.homeChecklistDrawLayout, binding.homeChecklistDrawIcon, mainActivityViewModel.checkListExpanded.value!!)
        }

        //지금 잡히는 리스트
        //곤충
        mainActivityViewModel.nowBugList.observe(viewLifecycleOwner){
            Log.d(TAG, "initObserve: ${it}")
            homeBugAdapter.list = it
//            homeBugAdapter.setNewList(it)
            homeBugAdapter.notifyDataSetChanged()
        }

        //물고기
        mainActivityViewModel.nowFishList.observe(viewLifecycleOwner){
//            Log.d(TAG, "initObserve: ${it}")
            homeFishAdapter.list = it
            homeFishAdapter.notifyDataSetChanged()
        }

        //해산물
        mainActivityViewModel.nowSeaList.observe(viewLifecycleOwner){
            homeSeaAdapter.list = it
            homeSeaAdapter.notifyDataSetChanged()
        }

        //주민 리스트
        mainActivityViewModel.myVillagerList.observe(viewLifecycleOwner){
            if(it.size == 0){
                binding.homeAddVillagerBtn.visibility = View.VISIBLE
                binding.homeVillagerRecyclerview.visibility = View.GONE
            } else {
                binding.homeAddVillagerBtn.visibility = View.GONE
                binding.homeVillagerRecyclerview.visibility = View.VISIBLE

            }
        }
    }

    private fun initListener() {
        //새로고침 버튼 이벤트
        binding.homeToolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.time_refresh_btn -> {
                    refreshTime()
                    refreshList()
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

        binding.homeChecklistHeadLayout.setOnClickListener {
            mainActivityViewModel.toggleCheckList()
        }

        // 체크리스트 리스너
        checkList.forEachIndexed { index, it ->
            it.setOnClickListener {
                val flg = ApplicationClass.sharedPreferencesUtil.setCheckList(index)
                toggleCheckList(it, flg)
            }
        }

        //주민 추가 버튼
        binding.homeAddVillagerBtn.setOnClickListener {
            mainActivity.moveNavItem(R.id.navigation_page_3)
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout_main, VillagerFragment())
                .commit()


            mainActivityViewModel.addVillager(190)
        }
    }

    fun initChecklist(){
        for(i:Int in 0..11){
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


    private fun initSetting(){
        checkList.addAll(arrayOf(binding.fossil1, binding.fossil2, binding.fossil3, binding.fossil4, binding.bottle, binding.moneyTree,
             binding.rock1, binding.rock2, binding.rock3, binding.rock4, binding.rock5, binding.rock6))
        binding.homeToolbar.inflateMenu(R.menu.home_appbar_item)
        refreshTime()
        refreshList()
        initChecklist()

        initBugAdapter()
        initFishAdapter()
        initSeaAdapter()

//        mainActivityViewModel.getNowBugList()
//        mainActivityViewModel.getNowFishList()
    }

    fun refreshTime(){
        mainActivityViewModel.setTime()
        mainActivityViewModel.convertLongToTime()
    }

    private fun refreshList(){
        mainActivityViewModel.getNowBugList()
        mainActivityViewModel.getNowFishList()
        mainActivityViewModel.getNowSeaList()
        mainActivityViewModel.getMyVilagerList()
    }

    //지금 잡히는 리스트 어댑터 초기화
    private fun initBugAdapter(){
        homeBugAdapter = HomeBugAdapter(mainActivity)
        homeBugAdapter.list = listOf()

        val manager = GridLayoutManager(context, 5)

        binding.homeBugRecyclerview.apply {
            layoutManager = manager
            adapter = homeBugAdapter
        }
    }

    private fun initFishAdapter(){
        homeFishAdapter = HomeFishAdapter(mainActivity)
        homeFishAdapter.list = listOf()

        val manager = GridLayoutManager(context, 5)

        binding.homeFishesRecyclerview.apply {
            layoutManager = manager
            adapter = homeFishAdapter
        }
    }

    private fun initSeaAdapter(){
        homeSeaAdapter = HomeSeaAdapter(mainActivity)
        homeSeaAdapter.list = listOf()

        val manager = GridLayoutManager(context, 5)

        binding.homeSeaRecyclerview.apply {
            layoutManager = manager
            adapter = homeSeaAdapter
        }
    }
}