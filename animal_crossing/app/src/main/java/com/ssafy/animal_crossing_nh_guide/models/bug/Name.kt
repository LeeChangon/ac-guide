package com.ssafy.animal_crossing_nh_guide.models.bug

import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("name_CNzh")var star: String,
    val name_EUde: String,
    val name_EUen: String,
    val name_EUes: String,
    val name_EUfr: String,
    val name_EUit: String,
    val name_EUnl: String,
    val name_EUru: String,
    val name_JPja: String,
    val name_KRko: String,
    val name_TWzh: String,
    val name_USen: String,
    val name_USes: String,
    val name_USfr: String
)