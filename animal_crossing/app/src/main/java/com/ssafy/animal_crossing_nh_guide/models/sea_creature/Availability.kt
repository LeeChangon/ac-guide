package com.ssafy.animal_crossing_nh_guide.models.sea_creature

data class Availability(
    val isAllDay: Boolean,
    val isAllYear: Boolean,
    val month_array_northern: List<Int>,
    val month_array_southern: List<Int>,
    val month_northern: String,
    val month_southern: String,
    val time: String,
    val time_array: List<Int>
)