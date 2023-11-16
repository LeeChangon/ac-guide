package com.ssafy.animal_crossing_nh_guide.models.sea_creature

data class South(
    val availability_array: List<AvailabilityArray>,
    val months: String,
    val months_array: List<Int>,
    val times_by_month: TimesByMonth //TimesByMonthX
)