package com.ssafy.animal_crossing_nh_guide.critterpedia.sea_creature

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.config.BaseDialogFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentSeaCreatureDetailDialogBinding
import com.ssafy.animal_crossing_nh_guide.models.sea_creature.SeaCreature

private const val TAG = "SeaCreatureDetailFragment_싸피"

class HomeSeaDetailFragment(val monthList: ArrayList<Boolean>, val item: SeaCreature) : BaseDialogFragment<FragmentSeaCreatureDetailDialogBinding>(
    FragmentSeaCreatureDetailDialogBinding::bind,
    R.layout.fragment_sea_creature_detail_dialog
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
        binding.seaCreature = item

        binding.closeBtn.setOnClickListener {
            this.dismiss()
        }

    }

}