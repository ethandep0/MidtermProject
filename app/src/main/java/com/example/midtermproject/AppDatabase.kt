package com.example.midtermproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Score::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scoreDao(): ScoreDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var ins = INSTANCE
                if (ins == null) {
                    ins = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "scores_database"
                    ).build()
                    INSTANCE = ins
                }
                return ins
            }
        }
    }
}
