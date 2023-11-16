package com.ssafy.animal_crossing_nh_guide.models.sea_creature

data class SeaCreature(
    val catchphrases: List<String>,
    val image_url: String,
    val name: String,
    val north: North,
    val number: Int,
    val rarity: String,
    val render_url: String,
    val sell_nook: Int,
    val shadow_movement: String,
    val shadow_size: String,
    val south: South,
    val tank_length: Int,
    val tank_width: Int,
    val total_catch: Int,
    val url: String
)