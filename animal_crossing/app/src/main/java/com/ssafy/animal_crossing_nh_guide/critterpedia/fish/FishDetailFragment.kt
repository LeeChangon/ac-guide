package com.ssafy.animal_crossing_nh_guide.critterpedia.fish

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.config.BaseDialogFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentFishDetailBinding

private const val TAG = "FishDetailFragment_싸피"

class FishDetailFragment(startPosition: Int) : FishDetailViewPagerAdapter.MyCallBack, BaseDialogFragment<FragmentFishDetailBinding>(
    FragmentFishDetailBinding::bind,
    R.layout.fragment_fish_detail
){

    private lateinit var viewPagerAdapter: FishDetailViewPagerAdapter
    private var currentPage = startPosition
    private var maxPage = 80

    private val fishFragmentViewModel: FishFragmentViewModel by viewModels({ requireParentFragment() })

    override fun onResume() {
        super.onResume()
        requireContext().dialogFragmentResize(this, 1f, 0.9f)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        maxPage = fishFragmentViewModel.fishList.value!!.size

        viewPagerAdapter = FishDetailViewPagerAdapter(fishFragmentViewModel)
        viewPagerAdapter.myCallBack = this
        binding.fishDetailViewPager.adapter = viewPagerAdapter
        binding.fishDetailViewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
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


        binding.fishDetailViewPager.setCurrentItem(currentPage, false)

        binding.btnPrev.setOnClickListener {
            if (currentPage > 0){
                binding.fishDetailViewPager.setCurrentItem(currentPage - 1)
            }
        }
        binding.btnNext.setOnClickListener {
            if (currentPage < maxPage) {
                binding.fishDetailViewPager.setCurrentItem(currentPage + 1)
            }
        }

    }

    override fun close() {
        this.dismiss()
    }


}