package com.continuouswave1550.fitness.fitnessprohome.exercise

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Exercise::class], version = 1, exportSchema = false)
abstract class ExerciseDatabase : RoomDatabase() {

    abstract val exerciseDao: ExerciseDao

    companion object {
        const val TAG = "ExerciseDatabase"

        @Volatile
        private var INSTANCE: ExerciseDatabase? = null

        fun getInstance(context: Context) : ExerciseDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ExerciseDatabase::class.java,
                        "sleep_history_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}