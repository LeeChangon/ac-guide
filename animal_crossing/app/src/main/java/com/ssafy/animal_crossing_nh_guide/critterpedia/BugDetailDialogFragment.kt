package com.ssafy.animal_crossing_nh_guide.critterpedia

import android.os.Bundle
import android.util.Log
import android.view.View
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.config.BaseFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentBugDetailDialogBinding
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentMonthBinding

class BugDetailDialogFragment : BaseFragment<FragmentBugDetailDialogBinding>(
    FragmentBugDetailDialogBinding::bind, R.layout.fragment_bug_detail_dialog){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}

