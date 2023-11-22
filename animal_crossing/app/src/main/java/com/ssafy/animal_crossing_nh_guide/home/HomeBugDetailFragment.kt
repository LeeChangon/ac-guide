package com.ssafy.animal_crossing_nh_guide.critterpedia.bug

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.config.BaseDialogFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentBugDetailDialogBinding
import com.ssafy.animal_crossing_nh_guide.models.bug.Bug

private const val TAG = "BugDetailFragment_싸피"

class HomeBugDetailFragment(val monthList: ArrayList<Boolean>, val item: Bug) : BaseDialogFragment<FragmentBugDetailDialogBinding>(
    FragmentBugDetailDialogBinding::bind,
    R.layout.fragment_bug_detail_dialog
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
        binding.bug = item

        binding.closeBtn.setOnClickListener {
            this.dismiss()
        }

    }

}