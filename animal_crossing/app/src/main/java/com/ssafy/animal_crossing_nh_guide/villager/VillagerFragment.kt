package com.ssafy.animal_crossing_nh_guide.villager

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
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
) {

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
//            if(it != galleryAdapter.list){
                galleryAdapter.list = it
                Log.d(TAG, "onViewCreated: ${it}")
                galleryAdapter.notifyDataSetChanged()
            binding.villagerRecyclerView.scheduleLayoutAnimation()
//            }
        }

        ArrayAdapter.createFromResource(mainActivity, R.array.species_array, android.R.layout.simple_spinner_item).also {
            adapter -> adapter.setDropDownViewResource(R.layout.dropdown_item)
            binding.speciesSpinner.adapter = adapter
        }
        binding.speciesSpinner.onItemSelectedListener = AdapterSpecies(viewModel)

        ArrayAdapter.createFromResource(mainActivity, R.array.gender_array, android.R.layout.simple_spinner_item).also{
            adapter -> adapter.setDropDownViewResource(R.layout.dropdown_item)
            binding.genderSpinner.adapter = adapter
        }

        binding.genderSpinner.onItemSelectedListener = AdapterGender(viewModel)

        binding.search.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.name = s.toString()
                viewModel.getSpinnerNameVillagerList(viewModel.species, viewModel.gender, viewModel.name)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }


    private fun initAdapter(){
        galleryAdapter.list = listOf()
        val manager = GridLayoutManager(context, 4)
        binding.villagerRecyclerView.apply {
            layoutManager = manager
            adapter = galleryAdapter
        }
        viewModel.getVillagerList()
    }



}