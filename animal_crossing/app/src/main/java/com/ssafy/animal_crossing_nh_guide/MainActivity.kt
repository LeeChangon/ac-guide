package com.ssafy.animal_crossing_nh_guide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssafy.animal_crossing_nh_guide.config.BaseActivity
import com.ssafy.animal_crossing_nh_guide.databinding.ActivityMainBinding
import com.ssafy.animal_crossing_nh_guide.fragment.HomeFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.navigation_page_1 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout_main, HomeFragment())
                        .commit()
                    true
                }
                R.id.navigation_page_2 -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.frame_layout_main, MenuFragment())
//                        .commit()
                    true
                }
                R.id.navigation_page_3 -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.frame_layout_main, MyPageFragment())
//                        .commit()
                    true
                }
                R.id.navigation_page_4 -> {
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