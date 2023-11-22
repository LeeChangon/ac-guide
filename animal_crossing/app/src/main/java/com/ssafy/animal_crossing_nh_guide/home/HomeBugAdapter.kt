package com.ssafy.animal_crossing_nh_guide.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.critterpedia.bug.HomeBugDetailFragment
import com.ssafy.animal_crossing_nh_guide.databinding.ListItemHomeBinding
import com.ssafy.animal_crossing_nh_guide.models.bug.Bug

private const val TAG = "HomeBugAdapter_싸피"
class HomeBugAdapter(val context: Context, val childFragmentManager: FragmentManager) : RecyclerView.Adapter<HomeBugAdapter.ViewHolder>() {

    var list = listOf<Bug>()
    inner class ViewHolder(private val binding: ListItemHomeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Bug, position: Int){
            binding.apply{
                var path = "icons/bugs/${item.file_name}.png"
                filepath = path

                if(item.name.star == "true") {
                    binding.starIv.visibility = View.VISIBLE
                } else {
                    binding.starIv.visibility = View.GONE
                }

                binding.itemImage.setOnClickListener {
//                    Log.d("싸피", "bind: ${item.file_name}")
//                    Log.d(TAG, "bind: ${position}")

                    //달 구하기
                    val monthList = arrayListOf<Boolean>()
                    for (i in 1..12){
                        if(item.availability.month_array_northern.contains(i)){
                            monthList.add(true)
                        }else{
                            monthList.add(false)
                        }
                    }

                    HomeBugDetailFragment(monthList, item).show(
                        childFragmentManager, "VillagerDetail"
                    )

//
//                    val builder = AlertDialog.Builder(context)
//
//                    val view = FragmentBugDetailDialogBinding.inflate(LayoutInflater.from(context))
//
//                    view.bug = item
//                    view.monthList = monthList
//
//                    builder.setView(view.root)
//                    val dialog = builder.create()
//                    dialog.show()
//
//                    view.closeBtn.setOnClickListener {
//                        dialog.dismiss()
//                    }
//
//                    dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
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