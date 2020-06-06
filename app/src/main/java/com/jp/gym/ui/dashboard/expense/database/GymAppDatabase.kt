package com.jp.gym.ui.dashboard.expense.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ExpenseData::class], version = 1, exportSchema = false)
abstract class GymAppDatabase : RoomDatabase() {

    abstract fun repoDao(): ExpenseDao

    companion object {

        private var appDatabase: GymAppDatabase? = null


        fun getInstance(context: Context): GymAppDatabase {
            if (appDatabase == null) {
                appDatabase =
                    Room.databaseBuilder(
                        context.applicationContext,
                        GymAppDatabase::class.java,
                        "expense.db"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return appDatabase!!
        }
    }

}