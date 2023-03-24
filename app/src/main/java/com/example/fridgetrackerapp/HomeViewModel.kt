package com.example.fridgetrackerapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.fridgetrackerapp.FridgeItemRepository

@HiltViewModel
class HomeViewModel @Inject constructor(private val fridgeItemRepository: FridgeItemRepository) :
    ViewModel() {

    val fridgeItemList: LiveData<List<FridgeItem>> = fridgeItemRepository.allItems

    val foundFridgeItem: LiveData<FridgeItem> = fridgeItemRepository.foundItem

    fun getAllFridgeItems() {
        fridgeItemRepository.getAllFridgeItems()
    }

    fun addFridgeItem(fridgeItem: FridgeItem) {
        fridgeItemRepository.addFridgeItem(fridgeItem)
        getAllFridgeItems()
    }

    fun updateFridgeItemDetails(fridgeItem: FridgeItem) {
        fridgeItemRepository.updateFridgeItemDetails(fridgeItem)
        getAllFridgeItems()
    }

    fun findItemById(itemId: Int) {
        fridgeItemRepository.findItemById(itemId)
    }

    fun deleteEmployee(fridgeItem: FridgeItem) {
        fridgeItemRepository.deleteFridgeItem(fridgeItem)
        getAllFridgeItems()
    }

    val employeeList: LiveData<List<FridgeItem>> = fridgeItemRepository.allItems

    val foundEmployee: LiveData<FridgeItem> = fridgeItemRepository.foundItem
}