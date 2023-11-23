package com.ssafy.animal_crossing_nh_guide.critterpedia.seacreature

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.critterpedia.seaCreature.SeaCreatureDetailFragment
import com.ssafy.animal_crossing_nh_guide.databinding.ListItemGalleryBinding
import com.ssafy.animal_crossing_nh_guide.models.sea_creature.SeaCreature

private const val TAG = "SeaCreatureGalleryAdapt"
class SeaCreatureGalleryAdapter() : RecyclerView.Adapter<SeaCreatureGalleryAdapter.ViewHolder>() {

    lateinit var childFragmentManager: FragmentManager

    var list = listOf<SeaCreature>()
    inner class ViewHolder(private val binding: ListItemGalleryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: SeaCreature, position: Int){
            binding.apply{
                var path = "icons/sea/${item.file_name}.png"
                filepath = path
                Log.d("싸피", "bind: ${position}")
                binding.itemImage.setOnClickListener {
                    Log.d("싸피", "bind: ${item.file_name}")
                    Log.d(TAG, "bind: ${position}")
                    SeaCreatureDetailFragment(position).show(
                        childFragmentManager, "SeaCreatureDetail"
                    )
                }
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
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim_pop_in_list)
        Log.d("싸피", "bind: ${list[position]}")
        val dto = list[position]
        holder.apply {
            bind(dto, position)
        }
    }

    override fun getItemCount() = list.size
}