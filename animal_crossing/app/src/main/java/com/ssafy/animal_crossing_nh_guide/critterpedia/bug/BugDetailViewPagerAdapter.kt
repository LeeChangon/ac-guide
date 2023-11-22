package com.ssafy.animal_crossing_nh_guide.critterpedia.bug

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.database.Alert
import com.ssafy.animal_crossing_nh_guide.database.Caught
import com.ssafy.animal_crossing_nh_guide.database.Star
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentBugDetailDialogBinding
import com.ssafy.animal_crossing_nh_guide.models.bug.Bug
import com.ssafy.animal_crossing_nh_guide.util.RetrofitUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "BugDetailViewPagerAdapt_싸피"
class BugDetailViewPagerAdapter(val bugFragmentViewModel: BugFragmentViewModel) : RecyclerView.Adapter<BugDetailViewPagerAdapter.ViewHolder>() {

    interface MyCallBack {
        fun close()
    }

    lateinit var myCallBack:MyCallBack

    var bug: Bug? = null
    var monthList: ArrayList<Boolean> = arrayListOf(false, false, false, false, false, false, false, false, false, false, false, false)

    var catchFlg = false;
    var starFlg = false;
    var alertFlg = false;

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BugDetailViewPagerAdapter.ViewHolder {
        val binding = FragmentBugDetailDialogBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )



//        CoroutineScope(Dispatchers.Main).launch {
//            withContext(Dispatchers.Main) {
//                bug = RetrofitUtil.bugService.getBug(startPosition)
//            }
//        }

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BugDetailViewPagerAdapter.ViewHolder, position: Int) {
        Log.d("싸피", "onBindViewHolder: ${position}")
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Main) {
                bug = RetrofitUtil.bugService.getBug(bugFragmentViewModel.bugList.value!![position].id-1)
            }
            withContext(Dispatchers.Main) {
                Log.d("싸피", "onBindViewHolder: ${bug!!.availability.month_array_northern}")
                for (i in 1..12){
                    if(bug!!.availability.month_array_northern.contains(i)){
                        monthList[i-1] = true
                    }else{
                        monthList[i-1] = false
                    }
                }
            }
            //데이터베이스에서 플래그 받아오기
            withContext(Dispatchers.Main) {
                if(bugFragmentViewModel.myRepository.getCaught("곤충", position) != null) catchFlg = true
                else catchFlg = false

                if(bugFragmentViewModel.myRepository.getStar("곤충", position) != null) starFlg = true
                else starFlg = false

                if(bugFragmentViewModel.myRepository.getAlert("곤충", position) != null) alertFlg = true
                else alertFlg = false
            }
            holder.bind(position)
        }
    }



    override fun getItemCount(): Int {
        return bugFragmentViewModel.bugList.value!!.size
    }

    inner class ViewHolder(private val binding: FragmentBugDetailDialogBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position : Int){
            binding.btnCheck.isSelected = catchFlg
            binding.btnStar.isSelected = starFlg
            binding.btnBell.isSelected = alertFlg

            binding.bug = bug
            binding.monthList = monthList
            Log.d("싸피", "bind: ${monthList}")

            binding.btnBell.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.Main) {
                        if (alertFlg) {
                            bugFragmentViewModel.myRepository.deleteAlert(Alert(position, "곤충"))
                        } else {
                            bugFragmentViewModel.myRepository.insertAlert(Alert(position, "곤충", bug!!.file_name))
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
                            bugFragmentViewModel.myRepository.deleteStar(Star(position, "곤충"))
                        } else {
                            bugFragmentViewModel.myRepository.insertStar(Star(position, "곤충", bug!!.file_name))
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
                            bugFragmentViewModel.myRepository.deleteCaught(Caught(position, "곤충"))
                        } else {
                            bugFragmentViewModel.myRepository.insertCaught(Caught(position, "곤충", bug!!.file_name))
                        }
                    }
                    catchFlg = !catchFlg
                    binding.btnCheck.isSelected = catchFlg
                    Log.d(TAG, "bind 온클릭: $catchFlg")
                }

            }

            binding.closeBtn.setOnClickListener {
                myCallBack.close()
            }
        }
    }

}