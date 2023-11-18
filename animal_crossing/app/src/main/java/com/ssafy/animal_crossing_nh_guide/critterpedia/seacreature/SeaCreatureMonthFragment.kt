package com.ssafy.animal_crossing_nh_guide.critterpedia.seacreature

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.activity.MainActivity
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.config.BaseFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentSeaCreatureMonthBinding

class SeaCreatureMonthFragment : BaseFragment<FragmentSeaCreatureMonthBinding>(
    FragmentSeaCreatureMonthBinding::bind,
    R.layout.fragment_sea_creature_month
) {

    private var month = -1

    private lateinit var mainActivity: MainActivity
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        month = arguments?.getInt("month") ?: -1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        Log.d(TAG, "onViewCreated: ")
        binding.month.text = "Sea : ${month.toString()}"

    }


}