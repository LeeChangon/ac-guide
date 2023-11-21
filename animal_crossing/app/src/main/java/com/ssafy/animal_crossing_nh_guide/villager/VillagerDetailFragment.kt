package com.ssafy.animal_crossing_nh_guide.critterpedia.villager

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.config.BaseDialogFragment
import com.ssafy.animal_crossing_nh_guide.critterpedia.seacreature.VillagerDetailFragment1
import com.ssafy.animal_crossing_nh_guide.critterpedia.seacreature.VillagerDetailFragment2
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentVillagerDetailBinding
import com.ssafy.animal_crossing_nh_guide.models.villager.AcnhVillager
import com.ssafy.animal_crossing_nh_guide.models.villager.Villager
import com.ssafy.animal_crossing_nh_guide.util.RetrofitUtil
import com.ssafy.animal_crossing_nh_guide.villager.VillagerFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "VillagerDetailFragment_싸피"

class VillagerDetailFragment(startPosition: Int) : BaseDialogFragment<FragmentVillagerDetailBinding>(
    FragmentVillagerDetailBinding::bind,
    R.layout.fragment_villager_detail
){

    private var currentPage = startPosition
    private var maxPage = 80

    private lateinit var villager : Villager
    private var filepath = ""

    private var front = true


    private val villagerFragmentViewModel: VillagerFragmentViewModel by viewModels({ requireParentFragment() })

    override fun onAttach(context: Context) {
        super.onAttach(context)
        villagerFragmentViewModel.getFilePath(villagerFragmentViewModel.villagerList.value!![currentPage].name.name_USen)
        villager = villagerFragmentViewModel.villagerList.value!![currentPage]
    }

    override fun onResume() {
        super.onResume()
        requireContext().dialogFragmentResize(this, 1.0f, 1.0f)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        villagerFragmentViewModel.filePath.observe(viewLifecycleOwner){
            filepath = it
            childFragmentManager.beginTransaction()
                .replace(R.id.flipframe, VillagerDetailFragment1(filepath, villager))
                .addToBackStack(null)
                .commit()
        }



        binding.flipframe.setOnClickListener {
            if (front){
                front = !front
                childFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out,
                    )
                    .replace(R.id.flipframe, VillagerDetailFragment2(filepath, villager))
                    .addToBackStack(null)
                    .commit()
            }
            else{
                front = !front
                childFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out,
                    )
                    .replace(R.id.flipframe, VillagerDetailFragment1(filepath, villager))
                    .addToBackStack(null)
                    .commit()
            }
        }

        if (villager.id == 1){
            binding.btnPrev.visibility = View.GONE
        }
        if(villager.id == 391){
            binding.btnNext.visibility = View.GONE
        }

        binding.btnPrev.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.flipframe, VillagerDetailFragment1(filepath, villager))
                .addToBackStack(null)
                .commit()
        }

        binding.closeBtn.setOnClickListener {
            this.dismiss()
        }

//        maxPage = villagerFragmentViewModel.villagerList.value!!.size
//
//
//        binding.btnPrev.setOnClickListener {
//            if (currentPage > 0){
//                binding.villagerDetailViewPager.setCurrentItem(currentPage - 1)
//            }
//        }
//        binding.btnNext.setOnClickListener {
//            if (currentPage < maxPage) {
//                binding.villagerDetailViewPager.setCurrentItem(currentPage + 1)
//            }
//        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return super.onCreateDialog(savedInstanceState)
    }

}