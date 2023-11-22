package com.ssafy.animal_crossing_nh_guide.critterpedia.seacreature

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.database.Alert
import com.ssafy.animal_crossing_nh_guide.database.Caught
import com.ssafy.animal_crossing_nh_guide.database.Star
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentSeaCreatureDetailDialogBinding
import com.ssafy.animal_crossing_nh_guide.models.sea_creature.SeaCreature
import com.ssafy.animal_crossing_nh_guide.util.FirebasePushUtil
import com.ssafy.animal_crossing_nh_guide.util.RetrofitUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "SeaCreatureDetailViewPa_싸피"
class SeaCreatureDetailViewPagerAdapter(val seaCreatureFragmentViewModel: SeaCreatureFragmentViewModel) : RecyclerView.Adapter<SeaCreatureDetailViewPagerAdapter.ViewHolder>() {

    interface MyCallBack {
        fun close()
    }

    lateinit var myCallBack:MyCallBack

    var seaCreature: SeaCreature? = null
    var monthList: ArrayList<Boolean> = arrayListOf(false, false, false, false, false, false, false, false, false, false, false, false)

    var catchFlg = false
    var starFlg = false
    var alertFlg = false

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
            withContext(Dispatchers.Main) {
                Log.d("싸피", "onBindViewHolder: ${seaCreature!!.availability.month_array_northern}")
                for (i in 1..12){
                    if(seaCreature!!.availability.month_array_northern.contains(i)){
                        monthList[i-1] = true
                    }else{
                        monthList[i-1] = false
                    }
                }

            }
            //데이터베이스에서 플래그 받아오기
            withContext(Dispatchers.Main) {
                if(seaCreatureFragmentViewModel.myRepository.getCaught("해산물", position) != null) catchFlg = true
                else catchFlg = false

                if(seaCreatureFragmentViewModel.myRepository.getStar("해산물", position) != null) starFlg = true
                else starFlg = false

                if(seaCreatureFragmentViewModel.myRepository.getAlert("해산물", position) != null) alertFlg = true
                else alertFlg = false
            }
            
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int {
        return seaCreatureFragmentViewModel.seaCreatureList.value!!.size
    }

    inner class ViewHolder(private val binding: FragmentSeaCreatureDetailDialogBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            binding.btnCheck.isSelected = catchFlg
            binding.btnStar.isSelected = starFlg
            binding.btnBell.isSelected = alertFlg

            binding.seaCreature = seaCreature
            binding.monthList = monthList

            binding.btnBell.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.Main) {
                        if (alertFlg) {
                            seaCreatureFragmentViewModel.myRepository.deleteAlert(Alert(position, "해산물"))
                        } else {
                            val monthList = FirebasePushUtil.getMonthList(seaCreature!!.availability.month_array_northern)
                            val hourList = FirebasePushUtil.getHourList(seaCreature!!.availability.time_array)
                            seaCreatureFragmentViewModel.myRepository.insertAlert(Alert(position, "해산물", seaCreature!!.file_name, monthList, hourList, seaCreature!!.name.name_KRko))
                        }
                    }
                    alertFlg = !alertFlg
                    binding.btnBell.isSelected = alertFlg
                    Log.d(TAG, "bind 온클릭: $alertFlg")
                }
            }
            binding.btnStar.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.Main) {
                        if (starFlg) {
                            seaCreatureFragmentViewModel.myRepository.deleteStar(Star(position, "해산물"))
                        } else {
                            seaCreatureFragmentViewModel.myRepository.insertStar(Star(position, "해산물", seaCreature!!.file_name))
                        }
                    }
                    starFlg = !starFlg
                    binding.btnStar.isSelected = starFlg
                    Log.d(TAG, "bind 온클릭: $starFlg")
                }
            }
            binding.btnCheck.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.Main) {
                        if (catchFlg) {
                            seaCreatureFragmentViewModel.myRepository.deleteCaught(Caught(position, "해산물"))
                        } else {
                            seaCreatureFragmentViewModel.myRepository.insertCaught(Caught(position, "해산물", seaCreature!!.file_name))
                        }
                    }
                    catchFlg = !catchFlg
                    binding.btnCheck.isSelected = catchFlg
                    Log.d(TAG, "bind 온클릭: $catchFlg")
                }

            }

            binding.closeBtn.setOnClickListener {
                myCallBack.close()
            }
        }
    }

}