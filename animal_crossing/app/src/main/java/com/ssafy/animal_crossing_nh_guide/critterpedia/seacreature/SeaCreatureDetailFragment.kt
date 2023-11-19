package com.ssafy.animal_crossing_nh_guide.critterpedia.seaCreature

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.config.BaseDialogFragment
import com.ssafy.animal_crossing_nh_guide.critterpedia.seacreature.SeaCreatureDetailViewPagerAdapter
import com.ssafy.animal_crossing_nh_guide.critterpedia.seacreature.SeaCreatureFragmentViewModel
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentSeaCreatureDetailBinding

private const val TAG = "SeaCreatureDetailFragment_싸피"

class SeaCreatureDetailFragment(startPosition: Int) : SeaCreatureDetailViewPagerAdapter.MyCallBack, BaseDialogFragment<FragmentSeaCreatureDetailBinding>(
    FragmentSeaCreatureDetailBinding::bind,
    R.layout.fragment_sea_creature_detail
){

    private lateinit var viewPagerAdapter: SeaCreatureDetailViewPagerAdapter
    private var currentPage = startPosition
    private var maxPage = 40

    private val seaCreatureFragmentViewModel: SeaCreatureFragmentViewModel by viewModels({ requireParentFragment() })

    override fun onResume() {
        super.onResume()
        requireContext().dialogFragmentResize(this, 1f, 0.9f)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        maxPage = seaCreatureFragmentViewModel.seaCreatureList.value!!.size

        viewPagerAdapter = SeaCreatureDetailViewPagerAdapter(seaCreatureFragmentViewModel)
        viewPagerAdapter.myCallBack = this
        binding.seaCreatureDetailViewPager.adapter = viewPagerAdapter
        binding.seaCreatureDetailViewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
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


        binding.seaCreatureDetailViewPager.setCurrentItem(currentPage, false)

        binding.btnPrev.setOnClickListener {
            if (currentPage > 0){
                binding.seaCreatureDetailViewPager.setCurrentItem(currentPage - 1)
            }
        }
        binding.btnNext.setOnClickListener {
            if (currentPage < maxPage) {
                binding.seaCreatureDetailViewPager.setCurrentItem(currentPage + 1)
            }
        }

    }

    override fun close() {
        this.dismiss()
    }


}