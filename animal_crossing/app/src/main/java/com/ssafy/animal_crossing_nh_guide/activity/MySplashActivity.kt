package com.ssafy.animal_crossing_nh_guide.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import com.bumptech.glide.Glide
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.config.ApplicationClass
import com.ssafy.animal_crossing_nh_guide.config.BaseActivity
import com.ssafy.animal_crossing_nh_guide.databinding.ActivitySplashBinding
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

        //new Intent(현재 context, 이동할 activity)
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

        CoroutineScope(Dispatchers.Main).launch {
//            delay(2500)
            withContext(Dispatchers.Main) {
                val list = RetrofitUtil.villagerService.getVillagerList()
                list.forEach {
                    Log.d(TAG, "loadImage: ${it.file_name}")
                    Glide.with(this@MySplashActivity)
                        .load("${ApplicationClass.IMGS_URL}icons/villagers/${it.file_name}.png")
                        .preload()
                }
            }
            withContext(Dispatchers.Main) {
                val list = RetrofitUtil.bugService.getBugList()
                list.forEach {
                    Log.d(TAG, "loadImage: ${it.file_name}")
                    Glide.with(this@MySplashActivity)
                        .load("${ApplicationClass.IMGS_URL}icons/bugs/${it.file_name}.png")
                        .preload();
                }
            }

            moveMain()
        }
    }
}