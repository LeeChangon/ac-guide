package com.ssafy.animal_crossing_nh_guide.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.config.BaseActivity
import com.ssafy.animal_crossing_nh_guide.databinding.ActivityMainBinding
import com.ssafy.animal_crossing_nh_guide.home.CritterpediaFragment
import com.ssafy.animal_crossing_nh_guide.home.HomeFragment
import com.ssafy.animal_crossing_nh_guide.mypage.MypageFragment

private const val TAG = "MainActivity_싸피"
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private val activityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.bottomNavigation.itemIconTintList = null

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout_main, HomeFragment())
            .commit()

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.navigation_page_1 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout_main, HomeFragment())
                        .commit()
                    true
                }
                R.id.navigation_page_2 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout_main, CritterpediaFragment())
                        .commit()
                    true
                }
                R.id.navigation_page_3 -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.frame_layout_main, MyPageFragment())
//                        .commit()
                    true
                }
                R.id.navigation_page_4 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout_main, MypageFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }

        binding.bottomNavigation.setOnItemReselectedListener { item ->
            // 재선택시 다시 랜더링 하지 않기 위해 수정
            if(binding.bottomNavigation.selectedItemId != item.itemId){
                binding.bottomNavigation.selectedItemId = item.itemId
            }
        }
    }
}