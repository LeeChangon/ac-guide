package com.ssafy.animal_crossing_nh_guide.models.fish

data class Availability(
    val allDay: Boolean,
    val allYear: Boolean,
    val isAllDay: Boolean,
    val isAllYear: Boolean,
    val location: String,
    val month_array_northern: List<Int>,
    val month_array_southern: List<Int>,
    val month_northern: String,
    val month_southern: String,
    val rarity: String,
    val time: String,
    val time_array: List<Int>
)