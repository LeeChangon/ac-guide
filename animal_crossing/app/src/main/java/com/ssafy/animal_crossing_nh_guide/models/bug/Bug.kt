package com.ssafy.animal_crossing_nh_guide.models.bug

data class Bug(
    val catchphrases: List<String>,
    val image_url: String,
    val location: String,
    val name: String,
    val north: North,
    val number: Int,
    val rarity: String,
    val render_url: String,
    val sell_flick: Int,
    val sell_nook: Int,
    val south: South,
    val tank_length: Int,
    val tank_width: Int,
    val total_catch: Int,
    val url: String
)