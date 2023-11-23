package com.ssafy.animal_crossing_nh_guide.home

import android.app.Dialog
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.config.BaseDialogFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentHomeCalendarBinding

class HomeCalendar : BaseDialogFragment<FragmentHomeCalendarBinding>(
    FragmentHomeCalendarBinding::bind,
    R.layout.fragment_home_calendar
) {
    override fun onResume() {
        super.onResume()
        requireContext().dialogFragmentResize(this, 0.8f, 0.6f)
    }
}