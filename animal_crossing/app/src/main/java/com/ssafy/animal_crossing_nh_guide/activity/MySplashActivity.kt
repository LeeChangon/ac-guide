package com.ssafy.animal_crossing_nh_guide.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.config.ApplicationClass
import com.ssafy.animal_crossing_nh_guide.config.BaseActivity
import com.ssafy.animal_crossing_nh_guide.databinding.ActivitySplashBinding
import com.ssafy.animal_crossing_nh_guide.models.bug.Bug
import com.ssafy.animal_crossing_nh_guide.models.fish.Fish
import com.ssafy.animal_crossing_nh_guide.models.sea_creature.SeaCreature
import com.ssafy.animal_crossing_nh_guide.models.villager.AcnhVillager
import com.ssafy.animal_crossing_nh_guide.models.villager.Villager
import com.ssafy.animal_crossing_nh_guide.util.RetrofitUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MySplashActivity_싸피"
class MySplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
//    private val activityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setAnim()
        loadImage()


    }

    private fun moveMain() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent) //intent 에 명시된 액티비티로 이동
        finish() //현재 액티비티 종료

    }

    private fun setAnim(){
        Glide.with(this).load(R.drawable.nook_tail).into(binding.splashIv)


        val animation = AnimationUtils.loadAnimation(baseContext, R.anim.rotate)
        animation.interpolator = LinearInterpolator()
        animation.repeatCount = Animation.INFINITE
        animation.duration = 4000


        val nookAnim = AnimationUtils.loadAnimation(this, R.anim.up_down)
        binding.splashIv.startAnimation(nookAnim)

        binding.splashEarth.startAnimation(animation)
    }

    private fun loadImage(){
        var list1 = listOf<AcnhVillager>()
        var list2 = listOf<Villager>()
        var list3 = listOf<Bug>()
        var list4 = listOf<Fish>()
        var list5 = listOf<SeaCreature>()

//            val list1 = RetrofitUtil.acnhService.getAcnhAllVillager()
//
//            list1.forEach {
//                CoroutineScope(Dispatchers.Main).launch {
//                    Glide.with(this@MySplashActivity)
//                        .load("${it.image_url}")
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .preload(50, 50)
//                }
//
//            }
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            withContext(Dispatchers.Main) {
                list2 = RetrofitUtil.villagerService.getVillagerList()
                
                list3 = RetrofitUtil.bugService.getBugList()

                list4 = RetrofitUtil.fishService.getFishList()

                list5 = RetrofitUtil.seaCreatureService.getSeaCreatureList()
                
            }
            list2.forEach {
                Log.d(TAG, "loadImage: ${it.file_name}")
                Glide.with(this@MySplashActivity)
                    .load("${ApplicationClass.IMGS_URL}icons/villagers/${it.file_name}.png")
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .preload()

            }
            list3.forEach {
                Glide.with(this@MySplashActivity)
                    .load("${ApplicationClass.IMGS_URL}icons/bugs/${it.file_name}.png")
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .preload()

            }
            list4.forEach {
                Glide.with(this@MySplashActivity)
                    .load("${ApplicationClass.IMGS_URL}icons/fish/${it.file_name}.png")
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .preload()

            }
            list5.forEach {
                Log.d(TAG, "loadImage: $it")
                Glide.with(this@MySplashActivity)
                    .load("${ApplicationClass.IMGS_URL}icons/sea/${it.file_name}.png")
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .preload()

            }
            moveMain()
        }

    }
}