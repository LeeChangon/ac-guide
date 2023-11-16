package com.ssafy.animal_crossing_nh_guide.models.fish

data class Fish(
    val catchphrases: List<String>,
    val image_url: String,
    val location: String,
    val name: String,
    val north: North,
    val number: Int,
    val rarity: String,
    val render_url: String,
    val sell_cj: Int,
    val sell_nook: Int,
    val shadow_size: String,
    val south: South,
    val tank_length: Int,
    val tank_width: Int,
    val total_catch: Int,
    val url: String
)