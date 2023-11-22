package com.ssafy.animal_crossing_nh_guide.critterpedia.fish

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.config.BaseDialogFragment
import com.ssafy.animal_crossing_nh_guide.database.Alert
import com.ssafy.animal_crossing_nh_guide.database.Caught
import com.ssafy.animal_crossing_nh_guide.database.MyRepository
import com.ssafy.animal_crossing_nh_guide.database.Star
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentFishDetailDialogBinding
import com.ssafy.animal_crossing_nh_guide.models.fish.Fish
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "FishDetailFragment_싸피"

class HomeFishDetailFragment(val monthList: ArrayList<Boolean>, val item: Fish) : BaseDialogFragment<FragmentFishDetailDialogBinding>(
    FragmentFishDetailDialogBinding::bind,
    R.layout.fragment_fish_detail_dialog
){

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    val myRepository = MyRepository.get()
    var catchFlg = false
    var alertFlg = false
    var starFlg = false
    
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

        //버튼 활성화
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Main) {
                if(myRepository.getCaught("물고기", item.id-1) != null) catchFlg = true
                else catchFlg = false

                if(myRepository.getStar("물고기", item.id-1) != null) starFlg = true
                else starFlg = false

                if(myRepository.getAlert("물고기", item.id-1) != null) alertFlg = true
                else alertFlg = false
            }
            binding.btnCheck.isSelected = catchFlg
            binding.btnStar.isSelected = starFlg
            binding.btnBell.isSelected = alertFlg

        }

        binding.btnBell.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.Main) {
                    if (alertFlg) {
                        myRepository.deleteAlert(Alert(item.id-1, "물고기"))
                    } else {
                        myRepository.insertAlert(Alert(item.id-1, "물고기"))
                    }
                }
                alertFlg = !alertFlg
                binding.btnBell.isSelected = alertFlg
                Log.d(TAG, "bind 온클릭: $alertFlg")
            }
        }
        binding.btnStar.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.Main) {
                    if (starFlg) {
                        myRepository.deleteStar(Star(item.id-1, "물고기"))
                    } else {
                        myRepository.insertStar(Star(item.id-1, "물고기"))
                    }
                }
                starFlg = !starFlg
                binding.btnStar.isSelected = starFlg
                Log.d(TAG, "bind 온클릭: $starFlg")
            }
        }
        binding.btnCheck.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.Main) {
                    if (catchFlg) {
                        myRepository.deleteCaught(Caught(item.id-1, "물고기"))
                    } else {
                        myRepository.insertCaught(Caught(item.id-1, "물고기"))
                    }
                }
                catchFlg = !catchFlg
                binding.btnCheck.isSelected = catchFlg
                Log.d(TAG, "bind 온클릭: $catchFlg")
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainActivityViewModel.getNowFishList()
    }

}