package com.ssafy.animal_crossing_nh_guide.villager

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.critterpedia.villager.VillagerDetailFragment
import com.ssafy.animal_crossing_nh_guide.databinding.ListItemGalleryBinding
import com.ssafy.animal_crossing_nh_guide.databinding.ListItemGalleryBindingImpl
import com.ssafy.animal_crossing_nh_guide.models.villager.AcnhVillager
import com.ssafy.animal_crossing_nh_guide.models.villager.Villager
import com.ssafy.animal_crossing_nh_guide.util.RetrofitUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "VillagerGalleryAdapter_싸피"
class VillagerGalleryAdapter(val context: Context, val villagerFragmentViewModel: VillagerFragmentViewModel) : RecyclerView.Adapter<VillagerGalleryAdapter.MyViewHolder>() {

    lateinit var childFragmentManager: FragmentManager

    var list = listOf<Villager>()
    var acnhV: AcnhVillager? = null
    var itemfilepath = ""

    inner class MyViewHolder (val binding: ListItemGalleryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Villager, position: Int){
            binding.apply{

                filepath = "icons/villagers/${item.file_name}.png"

                binding.itemImage.setOnClickListener {
                    VillagerDetailFragment(position).show(
                        childFragmentManager, "VillagerDetail"
                    )
                }
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_gallery,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim_pop_in_list)

        val dto = list[position]
        holder.apply {
            bind(dto, position)
        }
    }

    override fun getItemCount() = list.size

}