package com.ssafy.animal_crossing_nh_guide.villager

import android.util.Log
import android.view.View
import android.widget.AdapterView

private const val TAG = "AdapterSpecies_싸피"
class AdapterSpecies(val viewModel: VillagerFragmentViewModel):  AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d(TAG, "onItemSelected: Species${position}")
        viewModel.species = position
        viewModel.getSpinnerNameVillagerList(viewModel.species, viewModel.gender, viewModel.name)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

}