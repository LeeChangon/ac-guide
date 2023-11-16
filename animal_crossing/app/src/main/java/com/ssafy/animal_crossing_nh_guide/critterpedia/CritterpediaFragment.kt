package com.ssafy.animal_crossing_nh_guide.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ssafy.animal_crossing_nh_guide.activity.MainActivity
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.config.BaseFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentCritterpediaBinding
import com.ssafy.animal_crossing_nh_guide.models.bug.Bug


private const val TAG = "CritterpediaFragment_싸피"
class CritterpediaFragment : BaseFragment<FragmentCritterpediaBinding>(FragmentCritterpediaBinding::bind, R.layout.fragment_critterpedia) {

    private lateinit var mainActivity: MainActivity
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private var tabTextList = listOf("곤충", "물고기", "해산물")
    private var tabIconList = listOf(R.drawable.butterfly, R.drawable.fish, R.drawable.octopus)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")

        viewPagerAdapter = ViewPagerAdapter(this)
        binding.critterViewPager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.critterTabLayout, binding.critterViewPager) { tab, position ->
            tab.text = tabTextList[position]
            tab.setIcon(tabIconList[position])
        }.attach()

    }

}

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        lateinit var f:Fragment
        when (position) {
            0 -> {
                f = BugFragment()
            }
            1 -> {
                f  = FishFragment()
            }
            2 -> {
                f = SeaCreatureFragment()
            }
        }
        return f
    }
}