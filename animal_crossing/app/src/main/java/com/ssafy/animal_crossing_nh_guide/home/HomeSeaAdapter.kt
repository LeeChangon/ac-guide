package com.ssafy.animal_crossing_nh_guide.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.databinding.ListItemHomeBinding
import com.ssafy.animal_crossing_nh_guide.models.sea_creature.SeaCreature

private const val TAG = "HomeSeaAdapter_싸피"
class HomeSeaAdapter() : RecyclerView.Adapter<HomeSeaAdapter.ViewHolder>() {

//    lateinit var childFragmentManager: FragmentManager

    var list = listOf<SeaCreature>()
    inner class ViewHolder(private val binding: ListItemHomeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: SeaCreature, position: Int){
            binding.apply{
                var path = "icons/sea/${item.file_name}.png"
                filepath = path
                Log.d("싸피", "bind: ${position}")
                binding.itemImage.setOnClickListener {
                    Log.d("싸피", "bind: ${item.file_name}")
                    Log.d(TAG, "bind: ${position}")

//                    SeaDetailFragment(position).show(
//                        childFragmentManager, "SeaDetail"
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