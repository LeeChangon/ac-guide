package com.ssafy.animal_crossing_nh_guide.models

data class EventData(
    val year: Int,
    val month: Int,
    val day: List<Int>,
    val hour: List<Int>,
    val name: String,
    val text: String,
    val period: String
)