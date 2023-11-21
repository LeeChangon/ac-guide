package com.ssafy.animal_crossing_nh_guide.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MyDao {

    //잡았다
    @Query("select * from caught where type =(:type) and `index`=(:index)")
    suspend fun getCaught(type : String, index: Int) : Caught

    @Insert
    suspend fun insertCaught(caught: Caught)

    @Delete
    suspend fun deleteCaught(caught: Caught)

    //즐찾
    @Query("select * from Star where type =(:type) and `index`=(:index)")
    suspend fun getStar(type : String, index: Int) : Star

    @Insert
    suspend fun insertStar(Star: Star)

    @Delete
    suspend fun deleteStar(Star: Star)

    //알람
    @Query("select * from Alert where type =(:type) and `index`=(:index)")
    suspend fun getAlert(type : String, index: Int) : Alert

    @Insert
    suspend fun insertAlert(Alert: Alert)

    @Delete
    suspend fun deleteAlert(Alert: Alert)

    //주민
    @Query("select * from MyVillager")
    suspend fun getAllMyVillager(): List<MyVillager>

    @Query("select * from MyVillager where `index` =(:index)")
    suspend fun getMyVillager(index: Int) : MyVillager

    @Insert
    suspend fun insertMyVillager(myVillager: MyVillager)

    @Delete
    suspend fun deleteMyVillager(myVillager: MyVillager)
}