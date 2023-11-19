package com.ssafy.animal_crossing_nh_guide.critterpedia.bug

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentBugDetailDialogBinding
import com.ssafy.animal_crossing_nh_guide.models.bug.Bug
import com.ssafy.animal_crossing_nh_guide.util.RetrofitUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BugDetailViewPagerAdapter(val bugFragmentViewModel: BugFragmentViewModel) : RecyclerView.Adapter<BugDetailViewPagerAdapter.ViewHolder>() {

    interface MyCallBack {
        fun close()
    }

    lateinit var myCallBack:MyCallBack

    var bug: Bug? = null
    var monthList: ArrayList<Boolean> = arrayListOf(false, false, false, false, false, false, false, false, false, false, false, false)

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
            holder.bind()
        }
    }



    override fun getItemCount(): Int {
        return bugFragmentViewModel.bugList.value!!.size
    }

    inner class ViewHolder(private val binding: FragmentBugDetailDialogBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){

            binding.bug = bug
            binding.monthList = monthList
            Log.d("싸피", "bind: ${monthList}")

            binding.btnBell.setOnClickListener {
                binding.btnBell.isSelected = !binding.btnBell.isSelected
            }
            binding.btnStar.setOnClickListener {
                binding.btnStar.isSelected = !binding.btnStar.isSelected
            }
            binding.btnCheck.setOnClickListener {
                binding.btnCheck.isSelected = !binding.btnCheck.isSelected
            }

            binding.closeBtn.setOnClickListener {
                myCallBack.close()
            }
        }
    }

}