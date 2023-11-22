package com.ssafy.animal_crossing_nh_guide.critterpedia.fish

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.config.BaseDialogFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentFishDetailDialogBinding
import com.ssafy.animal_crossing_nh_guide.models.fish.Fish

private const val TAG = "FishDetailFragment_싸피"

class HomeFishDetailFragment(val monthList: ArrayList<Boolean>, val item: Fish) : BaseDialogFragment<FragmentFishDetailDialogBinding>(
    FragmentFishDetailDialogBinding::bind,
    R.layout.fragment_fish_detail_dialog
){

    override fun onAttach(context: Context) {
        super.onAttach(context)


    }

    override fun onResume() {
        super.onResume()
        requireContext().dialogFragmentResize(this, 0.8f, 0.8f)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding.monthList = monthList
        binding.fish = item

        binding.closeBtn.setOnClickListener {
            this.dismiss()
        }

    }

}