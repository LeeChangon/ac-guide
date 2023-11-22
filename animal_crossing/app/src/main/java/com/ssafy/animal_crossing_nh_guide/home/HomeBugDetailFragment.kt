package com.ssafy.animal_crossing_nh_guide.critterpedia.bug

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
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentBugDetailDialogBinding
import com.ssafy.animal_crossing_nh_guide.models.bug.Bug
import com.ssafy.animal_crossing_nh_guide.util.FirebasePushUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "BugDetailFragment_싸피"

class HomeBugDetailFragment(val monthList: ArrayList<Boolean>, val item: Bug) : BaseDialogFragment<FragmentBugDetailDialogBinding>(
    FragmentBugDetailDialogBinding::bind,
    R.layout.fragment_bug_detail_dialog
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
        binding.bug = item

        binding.closeBtn.setOnClickListener {
            this.dismiss()
        }

        //버튼 활성화
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Main) {
                if(myRepository.getCaught("곤충", item.id-1) != null) catchFlg = true
                else catchFlg = false

                if(myRepository.getStar("곤충", item.id-1) != null) starFlg = true
                else starFlg = false

                if(myRepository.getAlert("곤충", item.id-1) != null) alertFlg = true
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
                        myRepository.deleteAlert(Alert(item.id-1, "곤충"))
                    } else {
                        val monthList = FirebasePushUtil.getMonthList(item.availability.month_array_northern)
                        val hourList = FirebasePushUtil.getHourList(item.availability.time_array)
                        myRepository.insertAlert(Alert(item.id-1, "곤충", item.file_name, monthList, hourList, item.name.name_KRko))
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
                        myRepository.deleteStar(Star(item.id-1, "곤충"))
                    } else {
                        myRepository.insertStar(Star(item.id-1, "곤충", item.file_name))
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
                        myRepository.deleteCaught(Caught(item.id-1, "곤충"))
                    } else {
                        myRepository.insertCaught(Caught(item.id-1, "곤충", item.file_name))
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
        mainActivityViewModel.getNowBugList()
    }

}