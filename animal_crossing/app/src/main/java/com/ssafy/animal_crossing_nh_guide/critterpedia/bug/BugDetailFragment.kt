package com.ssafy.animal_crossing_nh_guide.critterpedia.bug

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.config.BaseDialogFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentBugDetailBinding

private const val TAG = "BugDetailFragment_싸피"

class BugDetailFragment(startPosition: Int) : BugDetailViewPagerAdapter.MyCallBack, BaseDialogFragment<FragmentBugDetailBinding>(
    FragmentBugDetailBinding::bind,
    R.layout.fragment_bug_detail
){

    private lateinit var viewPagerAdapter: BugDetailViewPagerAdapter
    private var currentPage = startPosition

    private val bugFragmentViewModel: BugFragmentViewModel by viewModels({ requireParentFragment() })

    override fun onResume() {
        super.onResume()
        requireContext().dialogFragmentResize(this, 1f, 0.9f)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)



        viewPagerAdapter = BugDetailViewPagerAdapter(bugFragmentViewModel)
        viewPagerAdapter.myCallBack = this
        binding.bugDetailViewPager.adapter = viewPagerAdapter
        binding.bugDetailViewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentPage = position

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


        binding.bugDetailViewPager.setCurrentItem(currentPage, false)

        binding.btnPrev.setOnClickListener {
            if (currentPage > 0){
                binding.bugDetailViewPager.setCurrentItem(currentPage - 1)
            }
        }
        binding.btnNext.setOnClickListener {
            if (currentPage < 80) {
                binding.bugDetailViewPager.setCurrentItem(currentPage + 1)
            }
        }

    }

    override fun close() {
        this.dismiss()
    }


}