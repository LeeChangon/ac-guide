package com.ssafy.animal_crossing_nh_guide.critterpedia.bug

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.activity.MainActivity
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.config.BaseFragment
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentBugMonthBinding

class BugMonthFragment : BaseFragment<FragmentBugMonthBinding>(
    FragmentBugMonthBinding::bind,
    R.layout.fragment_bug_month
) {

    private var month = -1

    private val viewModel: BugFragmentViewModel by viewModels()
    private var galleryAdapter: BugGalleryAdapter = BugGalleryAdapter()

    private lateinit var mainActivity: MainActivity
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
        galleryAdapter.childFragmentManager = childFragmentManager

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        month = arguments?.getInt("month") ?: -1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        viewModel.bugList.observe(viewLifecycleOwner){
            galleryAdapter.list = it
            galleryAdapter.notifyDataSetChanged()
        }


    }
    private fun initAdapter(){
        galleryAdapter.list = listOf()
        val manager = GridLayoutManager(context, 4)
        binding.bugRecyclerView.apply {
            layoutManager = manager
            adapter = galleryAdapter
        }
        viewModel.getBugList()
    }

}