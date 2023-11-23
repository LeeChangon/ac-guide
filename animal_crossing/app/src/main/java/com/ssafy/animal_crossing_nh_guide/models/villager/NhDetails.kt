package com.ssafy.animal_crossing_nh_guide.models.villager

import com.google.gson.annotations.SerializedName

data class NhDetails(
    val catchphrase: String,
    val clothing: String,
    val clothing_variation: String,
    val fav_colors: List<String>,
    val fav_styles: List<String>,
    val hobby: String,
    val house_exterior_url: String,
    val house_flooring: String,
    val house_interior_url: String,
    val house_music: String,
    val house_music_note: String,
    val house_wallpaper: String,
    val icon_url: String,
    val image_url: String,
    val photo_url: String,
    val quote: String,
    @SerializedName("sub-personality")
    val sub_personality: String
)