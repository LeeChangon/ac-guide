package com.ssafy.animal_crossing_nh_guide.critterpedia.fish

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.database.Alert
import com.ssafy.animal_crossing_nh_guide.database.Caught
import com.ssafy.animal_crossing_nh_guide.database.Star
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentFishDetailDialogBinding
import com.ssafy.animal_crossing_nh_guide.models.fish.Fish
import com.ssafy.animal_crossing_nh_guide.util.RetrofitUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "FishDetailViewPagerAdap_싸피"
class FishDetailViewPagerAdapter(val fishFragmentViewModel: FishFragmentViewModel) : RecyclerView.Adapter<FishDetailViewPagerAdapter.ViewHolder>() {

    interface MyCallBack {
        fun close()
    }

    lateinit var myCallBack:MyCallBack

    var fish: Fish? = null
    var monthList: ArrayList<Boolean> = arrayListOf(false, false, false, false, false, false, false, false, false, false, false, false)

    var catchFlg = false;
    var starFlg = false;
    var alertFlg = false;

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): FishDetailViewPagerAdapter.ViewHolder {
        val binding = FragmentFishDetailDialogBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )



//        CoroutineScope(Dispatchers.Main).launch {
//            withContext(Dispatchers.Main) {
//                fish = RetrofitUtil.fishService.getFish(startPosition)
//            }
//        }

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FishDetailViewPagerAdapter.ViewHolder, position: Int) {
        Log.d("싸피", "onBindViewHolder: ${position}")
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Main) {
                fish = RetrofitUtil.fishService.getFish(fishFragmentViewModel.fishList.value!![position].id-1)
            }
            withContext(Dispatchers.Main) {
                Log.d("싸피", "onBindViewHolder: ${fish!!.availability.month_array_northern}")
                for (i in 1..12){
                    if(fish!!.availability.month_array_northern.contains(i)){
                        monthList[i-1] = true
                    }else{
                        monthList[i-1] = false
                    }
                }

            }
            //데이터베이스에서 플래그 받아오기
            withContext(Dispatchers.Main) {
                if(fishFragmentViewModel.myRepository.getCaught("물고기", position) != null) catchFlg = true
                else catchFlg = false

                if(fishFragmentViewModel.myRepository.getStar("물고기", position) != null) starFlg = true
                else starFlg = false

                if(fishFragmentViewModel.myRepository.getAlert("물고기", position) != null) alertFlg = true
                else alertFlg = false
            }
            
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int {
        return fishFragmentViewModel.fishList.value!!.size
    }

    inner class ViewHolder(private val binding: FragmentFishDetailDialogBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position : Int){
            binding.btnCheck.isSelected = catchFlg
            binding.btnStar.isSelected = starFlg
            binding.btnBell.isSelected = alertFlg

            binding.fish = fish
            binding.monthList = monthList

            binding.btnBell.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.Main) {
                        if (alertFlg) {
                            fishFragmentViewModel.myRepository.deleteAlert(Alert(position, "물고기"))
                        } else {
                            fishFragmentViewModel.myRepository.insertAlert(Alert(position, "물고기", fish!!.file_name))
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
                            fishFragmentViewModel.myRepository.deleteStar(Star(position, "물고기"))
                        } else {
                            fishFragmentViewModel.myRepository.insertStar(Star(position, "물고기", fish!!.file_name))
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
                            fishFragmentViewModel.myRepository.deleteCaught(Caught(position, "물고기"))
                        } else {
                            fishFragmentViewModel.myRepository.insertCaught(Caught(position, "물고기", fish!!.file_name))
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