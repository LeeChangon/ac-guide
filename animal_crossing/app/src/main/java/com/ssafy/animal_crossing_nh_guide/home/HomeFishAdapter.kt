package com.ssafy.animal_crossing_nh_guide.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.databinding.ListItemHomeBinding
import com.ssafy.animal_crossing_nh_guide.models.fish.Fish

private const val TAG = "HomeFishAdapter_싸피"
class HomeFishAdapter() : RecyclerView.Adapter<HomeFishAdapter.ViewHolder>() {

//    lateinit var childFragmentManager: FragmentManager

    var list = listOf<Fish>()
    inner class ViewHolder(private val binding: ListItemHomeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Fish, position: Int){
            binding.apply{
                var path = "icons/fish/${item.file_name}.png"
                filepath = path
                Log.d("싸피", "bind: ${position}")
                binding.itemImage.setOnClickListener {
                    Log.d("싸피", "bind: ${item.file_name}")
                    Log.d(TAG, "bind: ${position}")

//                    FishDetailFragment(position).show(
//                        childFragmentManager, "FishDetail"
//                    )
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_home,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("싸피", "bind: ${list[position]}")
        val dto = list[position]
        holder.apply {
            bind(dto, position)
        }
    }

    override fun getItemCount() = list.size
}