package com.continuouswave1550.fitness.fitnessprohome.exercise

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ExerciseAdapter : RecyclerView.Adapter<ExerciseViewHolder>() {

    var exercies = listOf<Exercise>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return exercies.size
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}