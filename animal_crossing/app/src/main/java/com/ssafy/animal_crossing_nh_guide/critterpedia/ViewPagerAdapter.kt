package com.ssafy.animal_crossing_nh_guide.critterpedia

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ssafy.animal_crossing_nh_guide.critterpedia.bug.BugMonthFragment
import com.ssafy.animal_crossing_nh_guide.critterpedia.fish.FishMonthFragment
import com.ssafy.animal_crossing_nh_guide.critterpedia.seacreature.SeaCreatureMonthFragment

class ViewPagerAdapter(fragment: Fragment, var tab: String) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 13

    override fun createFragment(position: Int): Fragment {
        lateinit var fragment:Fragment
        when (tab) {
            "Bug" -> fragment = BugMonthFragment()
            "Fish" -> fragment = FishMonthFragment()
            "Sea" -> fragment = SeaCreatureMonthFragment()
        }
        fragment.arguments = Bundle().apply {
            putInt("month", position)
        }
        return fragment
    }
}