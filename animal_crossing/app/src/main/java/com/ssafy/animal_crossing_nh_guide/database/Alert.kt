package com.ssafy.animal_crossing_nh_guide.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alert")
data class Alert(
    @ColumnInfo var index : Int = -1,
    @ColumnInfo(name = "type") var type : String = "",
    @ColumnInfo var url : String = "",
    @ColumnInfo var month : List<Int>? = null,
    @ColumnInfo var time : List<Int>? = null,
    @ColumnInfo var name : String = ""
){
    @PrimaryKey(autoGenerate = true)
    var ID: Long = 0

    constructor(id:Long, index: Int, type: String, url: String, month: List<Int>, time: List<Int>, name: String): this(index, type, url, month, time, name){
        this.ID = id
    }
}
