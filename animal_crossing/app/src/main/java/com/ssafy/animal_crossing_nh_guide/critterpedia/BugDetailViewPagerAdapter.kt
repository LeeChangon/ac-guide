package com.ssafy.animal_crossing_nh_guide.critterpedia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentBugDetailDialogBinding

class BugDetailViewPagerAdapter : RecyclerView.Adapter<BugDetailViewPagerAdapter.ViewHolder>() {

    interface MyCallBack {
        fun close()
    }

    lateinit var myCallBack:MyCallBack

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BugDetailViewPagerAdapter.ViewHolder {
        val binding = FragmentBugDetailDialogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BugDetailViewPagerAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 80
    }

    inner class ViewHolder(private val binding: FragmentBugDetailDialogBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){
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