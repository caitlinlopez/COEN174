package com.example.fridgetrackerapp

import android.os.Parcelable
import androidx.annotation.*
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "FridgeItems")
data class FridgeItem(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "itemName")
    var itemName: String,

    @ColumnInfo(name = "itemDesc")
    var itemDesc: String,

    @ColumnInfo(name = "itemQty")
    var itemQty: String,

) : Parcelable
