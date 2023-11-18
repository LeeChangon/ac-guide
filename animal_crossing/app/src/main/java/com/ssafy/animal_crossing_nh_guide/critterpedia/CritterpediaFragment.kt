package com.ssafy.animal_crossing_nh_guide.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayout
import com.ssafy.animal_crossing_nh_guide.activity.MainActivity
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.config.BaseFragment
import com.ssafy.animal_crossing_nh_guide.critterpedia.bug.BugFragment
import com.ssafy.animal_crossing_nh_guide.critterpedia.fish.FishFragment
import com.ssafy.animal_crossing_nh_guide.critterpedia.seacreature.SeaCreatureFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentCritterpediaBinding


private const val TAG = "CritterpediaFragment_싸피"
class CritterpediaFragment : BaseFragment<FragmentCritterpediaBinding>(FragmentCritterpediaBinding::bind, R.layout.fragment_critterpedia) {

    private lateinit var mainActivity: MainActivity
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")

        childFragmentManager.beginTransaction()
            .replace(R.id.frame_layout_critter, BugFragment())
            .commit()

        binding.critterTabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                when(tab.position) {
                    0 -> {
                        childFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout_critter, BugFragment())
                            .commit()
                        true
                    }
                    1 -> {
                        childFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout_critter, FishFragment())
                            .commit()
                        true
                    }
                    2 -> {
                        childFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout_critter, SeaCreatureFragment())
                            .commit()
                        true
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

    }

}
