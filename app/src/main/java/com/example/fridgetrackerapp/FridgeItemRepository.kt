package com.example.fridgetrackerapp

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import com.example.fridgetrackerapp.FridgeItem
import com.example.fridgetrackerapp.FridgeItemDAO

class FridgeItemRepository(private val fridgeItemDAO: FridgeItemDAO) {

    val allItems = MutableLiveData<List<FridgeItem>>()
    val foundItem = MutableLiveData<FridgeItem>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addFridgeItem(newFridgeItem: FridgeItem) {
        coroutineScope.launch(Dispatchers.IO) {
            fridgeItemDAO.addFridgeItem(newFridgeItem)
        }
    }

    fun updateFridgeItemDetails(newFridgeItem: FridgeItem) {
        coroutineScope.launch(Dispatchers.IO) {
            fridgeItemDAO.updateFridgeItemDetails(newFridgeItem)
        }
    }

    fun getAllFridgeItems() {
        coroutineScope.launch(Dispatchers.IO) {
            allItems.postValue(fridgeItemDAO.getAllFridgeItems())
        }
    }

    fun deleteFridgeItem(fridgeItem: FridgeItem) {
        coroutineScope.launch(Dispatchers.IO) {
            fridgeItemDAO.deleteFridgeItem(fridgeItem)
        }
    }

    fun findItemById(itemId: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            foundItem.postValue(fridgeItemDAO.findItemById(itemId))
        }
    }
}