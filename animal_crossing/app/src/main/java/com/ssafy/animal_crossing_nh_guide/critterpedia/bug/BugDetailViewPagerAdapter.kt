package com.ssafy.animal_crossing_nh_guide.critterpedia.bug

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentBugDetailDialogBinding
import com.ssafy.animal_crossing_nh_guide.models.bug.Bug
import com.ssafy.animal_crossing_nh_guide.util.RetrofitUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BugDetailViewPagerAdapter(startPosition: Int) : RecyclerView.Adapter<BugDetailViewPagerAdapter.ViewHolder>() {

    interface MyCallBack {
        fun close()
    }

    lateinit var myCallBack:MyCallBack
//
//    var startPosition = startPosition
    var bug: Bug? = null

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
                bug = RetrofitUtil.bugService.getBug(position)
            }
            holder.bind()
        }
    }

    override fun getItemCount(): Int {
        return 80
    }

    inner class ViewHolder(private val binding: FragmentBugDetailDialogBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){

            binding.bug = bug

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