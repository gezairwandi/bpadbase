package com.geco.bpadapp.data.db

import android.content.Context
import androidx.room.Room
import com.geco.bpadapp.App

object DatabaseProvider {

    private var instance: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (instance == null) {
            synchronized(AppDatabase::class) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    App().db_name
                ).fallbackToDestructiveMigration().build()
            }
        }
        return instance!!
    }
}