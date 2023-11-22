package com.ssafy.animal_crossing_nh_guide.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.ssafy.animal_crossing_nh_guide.activity.MainActivity
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.config.ApplicationClass
import com.ssafy.animal_crossing_nh_guide.config.BaseFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentHomeBinding


private const val TAG = "HomeFragment_싸피"
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    private lateinit var mainActivity: MainActivity
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    private val checkList = mutableListOf<View>()
    private val villagerCheckList = mutableListOf<View>()

    private lateinit var homeBugAdapter: HomeBugAdapter
    private lateinit var homeFishAdapter: HomeFishAdapter
    private lateinit var homeSeaAdapter: HomeSeaAdapter

    var clickedFlg = false

//    var bugExpanded = false;
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeToolbar.inflateMenu(R.menu.home_appbar_item)

        initSetting()
        initListener()
        initObserve()
    }

    private fun initObserve() {
        mainActivityViewModel.currentTime.observe(viewLifecycleOwner){
            binding.time = mainActivityViewModel.convertLongToTime()
        }

        mainActivityViewModel.bugExpanded.observe(viewLifecycleOwner){
            if(clickedFlg)
                toggleCard(binding.homeBugDrawLayout, binding.homeBugDrawIcon, mainActivityViewModel.bugExpanded.value!!)
            else{
                if(it)
                    binding.homeBugDrawLayout.visibility = View.VISIBLE
                else
                    binding.homeBugDrawLayout.visibility = View.GONE
            }

        }

        mainActivityViewModel.fishExpanded.observe(viewLifecycleOwner){
            if(clickedFlg)
                toggleCard(binding.homeFishDrawLayout, binding.homeFishDrawIcon, mainActivityViewModel.fishExpanded.value!!)
            else{
                if(it)
                    binding.homeFishDrawLayout.visibility = View.VISIBLE
                else
                    binding.homeFishDrawLayout.visibility = View.GONE
            }
        }

        mainActivityViewModel.critterExpanded.observe(viewLifecycleOwner){
            if(clickedFlg)
                toggleCard(binding.homeCritterDrawLayout, binding.homeCritterDrawIcon, mainActivityViewModel.critterExpanded.value!!)
            else{
                if(it)
                    binding.homeCritterDrawLayout.visibility = View.VISIBLE
                else
                    binding.homeCritterDrawLayout.visibility = View.GONE
            }
        }

        mainActivityViewModel.checkListExpanded.observe(viewLifecycleOwner){
            if(clickedFlg)
                toggleCard(binding.homeChecklistDrawLayout, binding.homeChecklistDrawIcon, mainActivityViewModel.checkListExpanded.value!!)
            else{
                if(it)
                    binding.homeChecklistDrawLayout.visibility = View.VISIBLE
                else
                    binding.homeChecklistDrawLayout.visibility = View.GONE
            }
        }

        //지금 잡히는 리스트
        //곤충
        mainActivityViewModel.nowBugList.observe(viewLifecycleOwner){
            Log.d(TAG, "initObserve: ${it}")
            if(it != homeBugAdapter.list) {
                homeBugAdapter.list = it
                homeBugAdapter.notifyDataSetChanged()
            }
        }

        //물고기
        mainActivityViewModel.nowFishList.observe(viewLifecycleOwner){
//            Log.d(TAG, "initObserve: ${it}")
            if(it != homeFishAdapter.list) {
                homeFishAdapter.list = it
                homeFishAdapter.notifyDataSetChanged()
            }
        }

        //해산물
        mainActivityViewModel.nowSeaList.observe(viewLifecycleOwner){
            if(it != homeSeaAdapter.list) {
                homeSeaAdapter.list = it
                homeSeaAdapter.notifyDataSetChanged()
            }
        }

        //주민 리스트
        mainActivityViewModel.myVillagerList.observe(viewLifecycleOwner){
            if(it.size == 0){
                binding.homeAddVillagerBtn.visibility = View.VISIBLE
                binding.villagerChecklistLayout.visibility = View.GONE
            } else {
                binding.homeAddVillagerBtn.visibility = View.GONE
                binding.villagerChecklistLayout.visibility = View.VISIBLE

                var newList = arrayListOf<String>()
                for(i:Int in 0 .. 9){
                    if(i < it.size){
                        villagerCheckList[i].isEnabled = true
                        villagerCheckList[i].visibility = View.VISIBLE
                        Log.d(TAG, "initObserve: ${it[i].url}")
                        newList.add("icons/villagers/${it[i].url}.png")
                    } else {
                        villagerCheckList[i].isEnabled = false
                        villagerCheckList[i].visibility = View.INVISIBLE
                        ApplicationClass.sharedPreferencesUtil.setCheckListWithValue(i+12, false)
                        newList.add("icons/villagers/ham01.png")
                    }
                }
                binding.myVillagers = newList
                Log.d(TAG, "initObserve: 주민리스트:${binding.myVillagers}")
            }
            initVillagerCheckList()
            villagerCheckList
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
            clickedFlg = true
            mainActivityViewModel.toggleBug()
        }

        binding.homeFishHeadLayout.setOnClickListener {
            clickedFlg = true
            mainActivityViewModel.toggleFish()
        }

        binding.homeCritterHeadLayout.setOnClickListener {
            clickedFlg = true
            mainActivityViewModel.toggleCritter()
        }

        binding.homeChecklistHeadLayout.setOnClickListener {
            clickedFlg = true
            mainActivityViewModel.toggleCheckList()
        }

        // 체크리스트 리스너
        checkList.addAll(villagerCheckList)
        checkList.forEachIndexed { index, it ->
            it.setOnClickListener {
                val flg = ApplicationClass.sharedPreferencesUtil.setCheckList(index)
                toggleCheckList(it, flg)
            }
        }

        //주민 추가 버튼
        binding.homeAddVillagerBtn.setOnClickListener {
            mainActivity.moveNavItem(R.id.navigation_page_3)
//            parentFragmentManager.beginTransaction()
//                .add(R.id.frame_layout_main, VillagerFragment()).addToBackStack("")
//                .commit()


//            mainActivityViewModel.addVillager(190)
        }
    }

    private fun initChecklist(){
        for(i:Int in 0..11){
            val check = ApplicationClass.sharedPreferencesUtil.getCheckList(i)
            toggleCheckList(checkList[i], check)
        }
    }

    private fun initVillagerCheckList(){
        for(i:Int in 0..9){
            val check = ApplicationClass.sharedPreferencesUtil.getCheckList(i+12)
            toggleCheckList(villagerCheckList[i], check)
        }
    }

    private fun toggleCheckList(image : View, check: Boolean){
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

        villagerCheckList.addAll(arrayOf(binding.villager1, binding.villager2, binding.villager3, binding.villager4, binding.villager5,
            binding.villager6, binding.villager7, binding.villager8, binding.villager9, binding.villager10))
        binding.myVillagers = arrayListOf("icons/villagers/ham01.png", "icons/villagers/ham01.png", "icons/villagers/ham01.png", "icons/villagers/ham01.png", "icons/villagers/ham01.png", "icons/villagers/ham01.png", "icons/villagers/ham01.png", "icons/villagers/ham01.png", "icons/villagers/ham01.png", "icons/villagers/ham01.png")



        refreshTime()
        refreshList()
        initChecklist()
        initVillagerCheckList()

        //어댑터 초기화
        initBugAdapter()
        initFishAdapter()
        initSeaAdapter()

//        mainActivityViewModel.getNowBugList()
//        mainActivityViewModel.getNowFishList()
    }

    private fun refreshTime(){
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
        homeBugAdapter = HomeBugAdapter(mainActivity, childFragmentManager)
        homeBugAdapter.list = listOf()

        val manager = GridLayoutManager(context, 5)

        binding.homeBugRecyclerview.apply {
            layoutManager = manager
            adapter = homeBugAdapter
        }
    }

    private fun initFishAdapter(){
        homeFishAdapter = HomeFishAdapter(mainActivity, childFragmentManager)
        homeFishAdapter.list = listOf()

        val manager = GridLayoutManager(context, 5)

        binding.homeFishesRecyclerview.apply {
            layoutManager = manager
            adapter = homeFishAdapter
        }
    }

    private fun initSeaAdapter(){
        homeSeaAdapter = HomeSeaAdapter(mainActivity, childFragmentManager)
        homeSeaAdapter.list = listOf()

        val manager = GridLayoutManager(context, 5)

        binding.homeSeaRecyclerview.apply {
            layoutManager = manager
            adapter = homeSeaAdapter
        }
    }

}