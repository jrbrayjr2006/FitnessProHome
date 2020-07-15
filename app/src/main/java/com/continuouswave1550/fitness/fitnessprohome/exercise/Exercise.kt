package com.continuouswave1550.fitness.fitnessprohome.exercise

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_table")
class Exercise {
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
    @ColumnInfo(name = "exercise_name")
    val exerciseName: String = ""
    @ColumnInfo(name = "reps")
    val reps: Int = 0
    @ColumnInfo(name = "sets")
    val sets: Int = 0
    @ColumnInfo(name = "exercise_duration")
    val duration: Long = 0L
}