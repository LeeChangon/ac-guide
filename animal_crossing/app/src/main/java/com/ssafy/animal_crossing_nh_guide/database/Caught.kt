package com.ssafy.animal_crossing_nh_guide.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "caught")
data class Caught(
    @PrimaryKey var index : Int = -1,
    @ColumnInfo(name = "type") var type : String = ""
)
