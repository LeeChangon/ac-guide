package com.ssafy.animal_crossing_nh_guide.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "caught")
data class Caught(
    @ColumnInfo var index : Int = -1,
    @ColumnInfo(name = "type") var type : String = ""
){
    @PrimaryKey(autoGenerate = true)
    var ID: Long = 0

    constructor(id:Long, index: Int, type: String): this(index, type){
        this.ID = id
    }
}
