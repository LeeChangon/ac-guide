package com.ssafy.animal_crossing_nh_guide.models.fish

data class Fish(
    val availability: Availability,
    val catch_phrase: String,
    val file_name: String,
    val icon_uri: String,
    val id: Int,
    val image_uri: String,
    val museum_phrase: String,
    val name: Name,
    val price: Int,
    val price_cj: Int,
    val shadow: String
)