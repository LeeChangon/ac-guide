package com.ssafy.animal_crossing_nh_guide.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.config.BaseDialogFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentBugDetailBinding

private const val TAG = "BugDetailFragment_싸피"
class BugDetailFragment : BaseDialogFragment<FragmentBugDetailBinding>(FragmentBugDetailBinding::bind, R.layout.fragment_bug_detail){

    private lateinit var viewPagerAdapter: ScreenSlidePagerAdapter

    override fun onResume() {
        super.onResume()
        requireContext().dialogFragmentResize(this, 1f, 0.82f)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.closeBtn.setOnClickListener {
            dialog?.dismiss()
        }

        binding.btnBell.setOnClickListener {
            binding.btnBell.isSelected= !binding.btnBell.isSelected
        }
        binding.btnStar.setOnClickListener {
            binding.btnStar.isSelected= !binding.btnStar.isSelected
        }
        binding.btnCheck.setOnClickListener {
            binding.btnCheck.isSelected= !binding.btnCheck.isSelected
        }

        viewPagerAdapter = ScreenSlidePagerAdapter(this,)
        binding.bugDetailViewPager.adapter = viewPagerAdapter


//        binding.btnPrev.setOnClickListener {
//            parentFragmentManager.beginTransaction()
//        }

    }

    inner class ScreenSlidePagerAdapter(fragment: DialogFragment):FragmentStateAdapter(fragment){
        override fun getItemCount(): Int {
            return 80
        }

        override fun createFragment(position: Int): DialogFragment {
//            fragment.dismiss()
            return BugDetailFragment()
        }

    }

}