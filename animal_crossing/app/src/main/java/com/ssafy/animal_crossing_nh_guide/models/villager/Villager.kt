package com.ssafy.animal_crossing_nh_guide.models.villager

data class Villager(
    val birthday: String,
    val birthday_string: String,
    val bubble_color: String,
    val catch_phrase: String,
    val catch_translations: CatchTranslations,
    val file_name: String,
    val gender: String,
    val hobby: String,
    val icon_uri: String,
    val id: Int,
    val image_uri: String,
    val name: Name,
    val personality: String,
    val saying: String,
    val species: String,
    val subtype: String,
    val text_color: String
)