package com.ssafy.animal_crossing_nh_guide.models.fish

data class South(
    val availability_array: List<AvailabilityArray>,
    val months: String,
    val months_array: List<Int>,
    val times_by_month: TimesByMonth //TimesByMonthX
)