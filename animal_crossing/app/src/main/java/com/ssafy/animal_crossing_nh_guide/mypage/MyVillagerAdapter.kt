package com.ssafy.animal_crossing_nh_guide.mypage

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.critterpedia.bug.HomeBugDetailFragment
import com.ssafy.animal_crossing_nh_guide.database.MyRepository
import com.ssafy.animal_crossing_nh_guide.database.MyVillager
import com.ssafy.animal_crossing_nh_guide.databinding.ListItemHomeBinding
import com.ssafy.animal_crossing_nh_guide.databinding.ListItemMyVillagerBinding
import com.ssafy.animal_crossing_nh_guide.models.bug.Bug
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MyVillagerAdapter_싸피"
class MyVillagerAdapter(val context: Context, val mainActivityViewModel: MainActivityViewModel) : RecyclerView.Adapter<MyVillagerAdapter.ViewHolder>() {

//    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    val myRepository = MyRepository.get()
    var list = listOf<MyVillager>()
    inner class ViewHolder(private val binding: ListItemMyVillagerBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: MyVillager, position: Int){
            binding.apply{
                var path = "icons/villagers/${item.url}.png"
                filepath = path

                binding.itemImage.setOnClickListener {

                }

                binding.minusIv.setOnClickListener {
                    binding.minusIv.isEnabled = false
                    CoroutineScope(Dispatchers.Main).launch{
                        withContext(Dispatchers.Main){
                            myRepository.deleteMyVillager(item.index)
                            mainActivityViewModel.getMyVilagerList()
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
            bind(dto, position)
        }
    }

    override fun getItemCount() = list.size

}