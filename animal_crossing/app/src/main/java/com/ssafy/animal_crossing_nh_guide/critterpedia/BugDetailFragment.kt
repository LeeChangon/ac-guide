package com.ssafy.animal_crossing_nh_guide.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.config.BaseDialogFragment
import com.ssafy.animal_crossing_nh_guide.critterpedia.BugDetailViewPagerAdapter
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentBugDetailBinding

private const val TAG = "BugDetailFragment_싸피"
class BugDetailFragment(position: Int) : BugDetailViewPagerAdapter.MyCallBack, BaseDialogFragment<FragmentBugDetailBinding>(FragmentBugDetailBinding::bind, R.layout.fragment_bug_detail){

    private lateinit var viewPagerAdapter: BugDetailViewPagerAdapter
    private var currentPage = 0
    private val position = position

    override fun onResume() {
        super.onResume()
        requireContext().dialogFragmentResize(this, 1f, 0.9f)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)



        viewPagerAdapter = BugDetailViewPagerAdapter(position)
        viewPagerAdapter.myCallBack = this
        binding.bugDetailViewPager.adapter = viewPagerAdapter
        binding.bugDetailViewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentPage = position
                Log.d(TAG, "onPageSelected: ${position}")
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        }
        )

        binding.bugDetailViewPager.setCurrentItem(position)

        binding.btnPrev.setOnClickListener {
            if (currentPage > 0){
                binding.bugDetailViewPager.setCurrentItem(currentPage - 1)
            }
        }
        binding.btnNext.setOnClickListener {
            if (currentPage < 80) {
                binding.bugDetailViewPager.setCurrentItem(currentPage+1)
            }
        }

    }

    override fun close() {
        this.dismiss()
    }


}