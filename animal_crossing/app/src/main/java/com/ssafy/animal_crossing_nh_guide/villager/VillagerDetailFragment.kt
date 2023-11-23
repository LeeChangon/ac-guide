package com.ssafy.animal_crossing_nh_guide.critterpedia.villager

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.config.BaseDialogFragment
import com.ssafy.animal_crossing_nh_guide.critterpedia.seacreature.VillagerDetailFragment1
import com.ssafy.animal_crossing_nh_guide.critterpedia.seacreature.VillagerDetailFragment2
import com.ssafy.animal_crossing_nh_guide.database.MyRepository
import com.ssafy.animal_crossing_nh_guide.database.MyVillager
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentVillagerDetailBinding
import com.ssafy.animal_crossing_nh_guide.models.villager.Villager
import com.ssafy.animal_crossing_nh_guide.villager.OnSwipeTouchListener
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
    val myRepository = MyRepository.get()

    private var currentPage = startPosition
    private var maxPage = 80

    private lateinit var villager : Villager
    private var filepath = listOf<String>()

    private var front = true

    lateinit var myVillager: MyVillager
    var myVillagerFlg = false

    private val villagerFragmentViewModel: VillagerFragmentViewModel by viewModels({ requireParentFragment() })

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: try getfilepath")
//        villagerFragmentViewModel.getFilePath(villagerFragmentViewModel.villagerList.value!![currentPage].name.name_USen)
        villager = villagerFragmentViewModel.villagerList.value!![currentPage]

    }

    override fun onResume() {
        super.onResume()
        requireContext().dialogFragmentResize(this, 1.0f, 1.0f)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        rereshHomeBtn()

        binding.addMyVillagerBtn.setOnClickListener {
            Log.d(TAG, "onViewCreated: 플래그 $myVillagerFlg")
            CoroutineScope(Dispatchers.Main).launch {
                if(!myVillagerFlg) {
                    if(myRepository.getAllMyVillager().size == 10){
                        Toast.makeText(context, "주민은 최대 10명까지 등록 가능합니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        myVillager = MyVillager(villager.id - 1, villager.file_name, villager.name.name_KRko)
                        Log.d(TAG, "onViewCreated: 빌리저 등록: $myVillager")
                        myRepository.insertMyVillager(myVillager)
                        myVillagerFlg = !myVillagerFlg
                    }

                } else {
                    myRepository.deleteMyVillager(myVillager.index)
                    myVillagerFlg = !myVillagerFlg
                }
                binding.addMyVillagerBtn.isSelected = myVillagerFlg
            }
        }


        childFragmentManager.beginTransaction()
            .replace(R.id.flipframe, VillagerDetailFragment1(villagerFragmentViewModel, currentPage))
            .addToBackStack(null)
            .commit()


        binding.flipbutton.setOnTouchListener(object: OnSwipeTouchListener(requireContext()) {

            override fun onSwipeLeft() {
                flipLeft()
            }
            override fun onSwipeRight() {
                flipRight()
            }
            override fun onSwipeTop() {
//                Toast.makeText(requireContext(),"위로",Toast.LENGTH_SHORT).show()
            }
            override fun onSwipeBottom() {
//                Toast.makeText(requireContext(),"아래로",Toast.LENGTH_SHORT).show()
            }
        })

        binding.flipbutton.setOnClickListener {
            flipLeft()
        }

        if (currentPage == 0){
            binding.btnPrev.visibility = View.GONE
        }
        if(currentPage == villagerFragmentViewModel.villagerList.value!!.size-1){
            binding.btnNext.visibility = View.GONE
        }

        binding.btnPrev.setOnClickListener {
            goPrev()

            rereshHomeBtn()
        }

        binding.btnNext.setOnClickListener {
            goNext()
            rereshHomeBtn()
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

    private fun goPrev(){
        front = true
        filepath = listOf()

        Log.d(TAG, "onViewCreated: ${currentPage-1}")
        if(currentPage-1 <= 0){
            binding.btnPrev.visibility = View.GONE
        }
        else{
            binding.btnPrev.visibility = View.VISIBLE
        }

        if (currentPage != 0){
            binding.btnNext.visibility = View.VISIBLE

            currentPage -= 1
            villager = villagerFragmentViewModel.villagerList.value!![currentPage]
            childFragmentManager.beginTransaction()
                .replace(R.id.flipframe, VillagerDetailFragment1(villagerFragmentViewModel, currentPage))
                .addToBackStack(null)
                .commit()
        }
    }
    private fun goNext(){
        front = true
        Log.d(TAG, "onViewCreated: ${currentPage+1}")
        filepath = listOf()
        if (currentPage+1 >= villagerFragmentViewModel.villagerList.value!!.size-1){
            binding.btnNext.visibility = View.GONE
        }
        else{
            binding.btnNext.visibility = View.VISIBLE
        }
        if (currentPage != villagerFragmentViewModel.villagerList.value!!.size-1){
            binding.btnPrev.visibility = View.VISIBLE

            currentPage += 1
            villager = villagerFragmentViewModel.villagerList.value!![currentPage]
            childFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.to_left, R.anim.from_right)
                .replace(R.id.flipframe, VillagerDetailFragment1(villagerFragmentViewModel, currentPage))
                .addToBackStack(null)
                .commit()
        }
    }

    private fun flipLeft(){
        if (front){
            front = !front
            childFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.animator.card_flip_right_in,
                    R.animator.card_flip_right_out,
                    R.animator.card_flip_left_in,
                    R.animator.card_flip_left_out,
                )
                .replace(R.id.flipframe, VillagerDetailFragment2(villagerFragmentViewModel, currentPage))
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
                .replace(R.id.flipframe, VillagerDetailFragment1(villagerFragmentViewModel, currentPage))
                .addToBackStack(null)
                .commit()
        }
    }
    private fun flipRight(){
        if (front){
            front = !front
            childFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.animator.card_flip_left_in,
                    R.animator.card_flip_left_out,
                    R.animator.card_flip_right_in,
                    R.animator.card_flip_right_out,
                )
                .replace(R.id.flipframe, VillagerDetailFragment2(villagerFragmentViewModel, currentPage))
                .addToBackStack(null)
                .commit()
        }
        else{
            front = !front
            childFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.animator.card_flip_left_in,
                    R.animator.card_flip_left_out,
                    R.animator.card_flip_right_in,
                    R.animator.card_flip_right_out,
                )
                .replace(R.id.flipframe, VillagerDetailFragment1(villagerFragmentViewModel, currentPage))
                .addToBackStack(null)
                .commit()
        }
    }

    private fun rereshHomeBtn(){
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Main){
                val tmpVillager = myRepository.getMyVillager(villager.id-1)

                if(tmpVillager!=null){
                    myVillager = tmpVillager
                    myVillagerFlg = true
                } else {
                    myVillagerFlg = false
                }
            }
            binding.addMyVillagerBtn.isSelected = myVillagerFlg
        }

    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return super.onCreateDialog(savedInstanceState)
    }

}