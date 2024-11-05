package com.coderobust.constructioncosttracker.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.coderobust.constructioncosttracker.data.Project
import com.coderobust.constructioncosttracker.data.ProjectCostItem

@Database(entities = [Project::class, ProjectCostItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
    abstract fun projectCostItemDao(): ProjectCostItemDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(ctx: Context): AppDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    ctx, AppDatabase::class.java,
                    "database_v2"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

            return instance!!

        }
    }
}
