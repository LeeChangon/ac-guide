package com.ssafy.animal_crossing_nh_guide.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "star")
data class Star(
    @ColumnInfo var index : Int = -1,
    @ColumnInfo(name = "type") var type : String = "",
    @ColumnInfo var url : String = ""
){
    @PrimaryKey(autoGenerate = true)
    var ID: Long = 0

    constructor(id:Long, index: Int, type: String, url: String): this(index, type, url){
        this.ID = id
    }
}
