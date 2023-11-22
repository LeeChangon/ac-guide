package com.ssafy.animal_crossing_nh_guide.mypage

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.database.Caught
import com.ssafy.animal_crossing_nh_guide.database.MyRepository
import com.ssafy.animal_crossing_nh_guide.database.MyVillager
import com.ssafy.animal_crossing_nh_guide.databinding.ListItemMyVillagerBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyCaughtAdapter(val context: Context, val mainActivityViewModel: MainActivityViewModel) : RecyclerView.Adapter<MyCaughtAdapter.ViewHolder>() {

//    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    val myRepository = MyRepository.get()
    var list = listOf<Caught>()
    inner class ViewHolder(private val binding: ListItemMyVillagerBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Caught){
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
//                    CoroutineScope(Dispatchers.Main).launch{
//                        withContext(Dispatchers.Main) {
//                            myRepository.deleteMyVillager(item.index)
//                            mainActivityViewModel.getMyVilagerList()
//                        }
//                        notifyItemRemoved(layoutPosition)
//                    }
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_my_villager,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("싸피", "bind: ${list[position]}")
        val dto = list[position]
        holder.apply {
            bind(dto)
        }
    }

    override fun getItemCount() = list.size

}