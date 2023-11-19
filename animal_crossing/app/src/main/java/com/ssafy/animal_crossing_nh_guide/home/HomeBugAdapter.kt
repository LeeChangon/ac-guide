package com.ssafy.animal_crossing_nh_guide.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.ssafy.animal_crossing_nh_guide.R
import com.ssafy.animal_crossing_nh_guide.activity.MainActivity
import com.ssafy.animal_crossing_nh_guide.databinding.ListItemHomeBinding
import com.ssafy.animal_crossing_nh_guide.models.bug.Bug

private const val TAG = "HomeBugAdapter_싸피"
class HomeBugAdapter(val context: Context, val list : List<Bug>) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size;
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(p0: Int): Long {
        return -1
    }

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_home, null)

        val binding2 = ListItemHomeBinding.bind(view)

        var path = "icons/bugs/${list[position].file_name}.png"

        binding2.filepath = path
        view.tag = list[position]
        Log.d(TAG, "getView: ${position}, ${list[position]}")

        return view
    }


}