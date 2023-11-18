package com.ssafy.animal_crossing_nh_guide.critterpedia.seacreature

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentSeaCreatureDetailDialogBinding
import com.ssafy.animal_crossing_nh_guide.models.sea_creature.SeaCreature
import com.ssafy.animal_crossing_nh_guide.util.RetrofitUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SeaCreatureDetailViewPagerAdapter(val seaCreatureFragmentViewModel: SeaCreatureFragmentViewModel) : RecyclerView.Adapter<SeaCreatureDetailViewPagerAdapter.ViewHolder>() {

    interface MyCallBack {
        fun close()
    }

    lateinit var myCallBack:MyCallBack

    var seaCreature: SeaCreature? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SeaCreatureDetailViewPagerAdapter.ViewHolder {
        val binding = FragmentSeaCreatureDetailDialogBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )



//        CoroutineScope(Dispatchers.Main).launch {
//            withContext(Dispatchers.Main) {
//                seaCreature = RetrofitUtil.seaCreatureService.getSeaCreature(startPosition)
//            }
//        }

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeaCreatureDetailViewPagerAdapter.ViewHolder, position: Int) {
        Log.d("싸피", "onBindViewHolder: ${position}")
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Main) {
                seaCreature = RetrofitUtil.seaCreatureService.getSeaCreature(seaCreatureFragmentViewModel.seaCreatureList.value!![position].id-1)
            }
            holder.bind()
        }
    }

    override fun getItemCount(): Int {
        return 80
    }

    inner class ViewHolder(private val binding: FragmentSeaCreatureDetailDialogBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){

            binding.seaCreature = seaCreature

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