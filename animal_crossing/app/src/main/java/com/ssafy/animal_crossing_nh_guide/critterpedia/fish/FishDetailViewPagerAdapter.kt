package com.ssafy.animal_crossing_nh_guide.critterpedia.fish

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentFishDetailDialogBinding
import com.ssafy.animal_crossing_nh_guide.models.fish.Fish
import com.ssafy.animal_crossing_nh_guide.util.RetrofitUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FishDetailViewPagerAdapter(val fishFragmentViewModel: FishFragmentViewModel) : RecyclerView.Adapter<FishDetailViewPagerAdapter.ViewHolder>() {

    interface MyCallBack {
        fun close()
    }

    lateinit var myCallBack:MyCallBack

    var fish: Fish? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): FishDetailViewPagerAdapter.ViewHolder {
        val binding = FragmentFishDetailDialogBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )



//        CoroutineScope(Dispatchers.Main).launch {
//            withContext(Dispatchers.Main) {
//                fish = RetrofitUtil.fishService.getFish(startPosition)
//            }
//        }

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FishDetailViewPagerAdapter.ViewHolder, position: Int) {
        Log.d("싸피", "onBindViewHolder: ${position}")
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Main) {
                fish = RetrofitUtil.fishService.getFish(fishFragmentViewModel.fishList.value!![position].id-1)
            }
            holder.bind()
        }
    }

    override fun getItemCount(): Int {
        return 80
    }

    inner class ViewHolder(private val binding: FragmentFishDetailDialogBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){

            binding.fish = fish

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