package com.continuouswave1550.fitness.fitnessprohome.exercise

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ExerciseDao {

    @Insert
    fun insert(exercise: Exercise)

    @Update
    fun update(exercise: Exercise)

    @Query("SELECT * FROM exercise_table WHERE id = :key")
    fun get(key: Long)
}