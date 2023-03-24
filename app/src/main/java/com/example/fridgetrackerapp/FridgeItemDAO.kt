package com.example.fridgetrackerapp

import androidx.room.*

@Dao
interface FridgeItemDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFridgeItem(fridgeItem: FridgeItem)

    @Query("SELECT * FROM FridgeItems WHERE id = :id")
    fun findItemById(id: Int): FridgeItem

    @Query("SELECT * FROM FridgeItems")
    fun getAllFridgeItems(): List<FridgeItem>

    @Update
    suspend fun updateFridgeItemDetails(fridgeItem: FridgeItem)

    @Delete
    suspend fun deleteFridgeItem(FridgeItem: FridgeItem)

}