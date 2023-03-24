package com.example.fridgetrackerapp.ui.theme

import android.content.*
import androidx.room.*
import com.example.fridgetrackerapp.*

//https://medium.com/jetpack-composers/jetpack-compose-room-db-b7b23bd6b189


@Database(entities = [(FridgeItem::class)], version = 1, exportSchema = false)
abstract class FridgeItemRoomDatabase : RoomDatabase() {

    abstract fun fridgeItemDAO(): FridgeItemDAO

    companion object {
        /*The value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory.
        This helps make sure the value of INSTANCE is always up-to-date and the same for all execution threads.
        It means that changes made by one thread to INSTANCE are visible to all other threads immediately.*/
        @Volatile
        private var INSTANCE: FridgeItemRoomDatabase? = null

        fun getInstance(context: Context): FridgeItemRoomDatabase {
            // only one thread of execution at a time can enter this block of code
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FridgeItemRoomDatabase::class.java,
                        "fridgeItem_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}