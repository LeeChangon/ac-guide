package com.ssafy.animal_crossing_nh_guide.villager

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.activity.MainActivity
import com.ssafy.animal_crossing_nh_guide.activity.MainActivityViewModel
import com.ssafy.animal_crossing_nh_guide.config.BaseFragment
import com.ssafy.animal_crossing_nh_guide.critterpedia.ViewPagerAdapter
import com.ssafy.animal_crossing_nh_guide.databinding.FragmentVillagerBinding

private const val TAG = "VillagerFragment_싸피"
class VillagerFragment : BaseFragment<FragmentVillagerBinding>(
    FragmentVillagerBinding::bind,
    R.layout.fragment_villager
), AdapterView.OnItemSelectedListener {

    private val viewModel: VillagerFragmentViewModel by viewModels()
    private lateinit var galleryAdapter: VillagerGalleryAdapter

    private lateinit var mainActivity: MainActivity
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
        galleryAdapter = VillagerGalleryAdapter(requireContext(), viewModel)
        galleryAdapter.childFragmentManager = childFragmentManager
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initAdapter()

        viewModel.villagerList.observe(viewLifecycleOwner){
            galleryAdapter.list = it
            Log.d(TAG, "onViewCreated: ${it}")
            galleryAdapter.notifyDataSetChanged()
        }

        ArrayAdapter.createFromResource(mainActivity, R.array.species_array, android.R.layout.simple_spinner_item).also {
            adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.speciesSpinner.adapter = adapter
        }
        binding.speciesSpinner.onItemSelectedListener = this


    }
    private fun initAdapter(){
        galleryAdapter.list = listOf()
        val manager = GridLayoutManager(context, 4)
        binding.villagerRecyclerView.apply {
            layoutManager = manager
            adapter = galleryAdapter
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d(TAG, "onItemSelected: ${id}")
        if (position == 0){
            viewModel.getVillagerList()
        }
        else{
            viewModel.getSpeciesVillagerList(position-1)
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }



}