package com.ssafy.animal_crossing_nh_guide.villager

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.databinding.ListItemGalleryBinding
import com.ssafy.animal_crossing_nh_guide.models.villager.Villager

class VillagerGalleryAdapter() : RecyclerView.Adapter<VillagerGalleryAdapter.ViewHolder>() {

    lateinit var childFragmentManager: FragmentManager

    var list = listOf<Villager>()
    inner class ViewHolder(private val binding: ListItemGalleryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Villager, position: Int){
            binding.apply{
                var path = "icons/villagers/${item.file_name}.png"
                filepath = path
//                Log.d("싸피", "bind: ${position}")
//                binding.itemImage.setOnClickListener {
//                    Log.d("싸피", "bind: ${item.file_name}")
//                    Log.d(TAG, "bind: ${position}")
//                    VillagerDetailFragment(position).show(
//                        childFragmentManager, "VillagerDetail"
//                    )
//                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_gallery,
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