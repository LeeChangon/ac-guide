package com.ssafy.animal_crossing_nh_guide.critterpedia.seacreature

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.config.BaseFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentVillagerDetail2Binding
import com.ssafy.animal_crossing_nh_guide.models.villager.Villager
import com.ssafy.animal_crossing_nh_guide.villager.VillagerFragmentViewModel

private const val TAG = "VillagerDetailFragment2_싸피"
class VillagerDetailFragment2(val villagerFragmentViewModel: VillagerFragmentViewModel, var currentPage: Int) : BaseFragment<FragmentVillagerDetail2Binding>(
    FragmentVillagerDetail2Binding::bind, R.layout.fragment_villager_detail2
){

    override fun onAttach(context: Context) {
        super.onAttach(context)
        villagerFragmentViewModel.getFilePath(villagerFragmentViewModel.villagerList.value!![currentPage].name.name_USen)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.cameraDistance = 8000*requireContext().resources.displayMetrics.density

        villagerFragmentViewModel.filePath.observe(viewLifecycleOwner){
            binding.filepath = it[1]
            binding.villager = villagerFragmentViewModel.villagerList.value!![currentPage]
        }

        val birthday = villagerFragmentViewModel.villagerList.value!![currentPage].birthday.split("*", "/")
        binding.bmonth = birthday[1]
        binding.bday = birthday[0]
        Log.d(TAG, "onViewCreated: ${birthday}")

    }
}