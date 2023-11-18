package com.ssafy.animal_crossing_nh_guide.critterpedia.fish

import android.os.Bundle
import android.view.View
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.config.BaseFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentBugDetailDialogBinding

class FishDetailDialogFragment : BaseFragment<FragmentBugDetailDialogBinding>(
    FragmentBugDetailDialogBinding::bind, R.layout.fragment_fish_detail_dialog
){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}