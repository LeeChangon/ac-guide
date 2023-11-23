package com.ssafy.animal_crossing_nh_guide.mypage

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.database.Alert
import com.ssafy.animal_crossing_nh_guide.database.MyRepository
import com.ssafy.animal_crossing_nh_guide.database.MyVillager
import com.ssafy.animal_crossing_nh_guide.database.Star
import com.ssafy.animal_crossing_nh_guide.databinding.ListItemMyCrittersBinding
import com.ssafy.animal_crossing_nh_guide.databinding.ListItemMyVillagerBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyStarAdapter(val context: Context, val mainActivityViewModel: MainActivityViewModel) : RecyclerView.Adapter<MyStarAdapter.ViewHolder>() {

//    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    val myRepository = MyRepository.get()
    var list = listOf<Star>()
    inner class ViewHolder(private val binding: ListItemMyCrittersBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Star){
            binding.apply{
                var nowType = ""
                if(item.type == "곤충") nowType = "bugs"
                else if(item.type == "물고기") nowType = "fish"
                else if(item.type == "해산물") nowType = "sea"


                var path = "icons/${nowType}/${item.url}.png"
                filepath = path

                binding.itemImage.setOnClickListener {

                }

                binding.minusIv.setOnClickListener {
                    CoroutineScope(Dispatchers.Main).launch{
                        withContext(Dispatchers.Main) {
                            myRepository.deleteStar(item)
                            mainActivityViewModel.getStarList()
                        }
                        notifyItemRemoved(layoutPosition)
                    }
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_my_critters,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim_pop_in_slow_list)

        Log.d("싸피", "bind: ${list[position]}")
        val dto = list[position]
        holder.apply {
            bind(dto)
        }
    }

    override fun getItemCount() = list.size

}