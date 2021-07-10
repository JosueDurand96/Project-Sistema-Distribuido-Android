package com.durand.vacunacionperu.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LocalVacunacion::class], version = 2)
abstract class AppLocalDatabase: RoomDatabase() {

    abstract fun localDao(): LocalVacunacionDao

    companion object {
        private var INSTANCE: AppLocalDatabase? = null

        fun getInstance(context: Context): AppLocalDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppLocalDatabase::class.java, "database-local2"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
            }
            return INSTANCE!!
        }

    }

}