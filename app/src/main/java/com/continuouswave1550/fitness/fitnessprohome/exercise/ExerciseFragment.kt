package com.continuouswave1550.fitness.fitnessprohome.exercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.continuouswave1550.fitness.fitnessprohome.R
import com.continuouswave1550.fitness.fitnessprohome.databinding.FragmentExerciseBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ExerciseFragment : Fragment() {

    private lateinit var binding: FragmentExerciseBinding
    @Inject lateinit var viewModel: ExerciseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exercise, container, false)
        return binding.root
    }

    companion object {
       const val TAG = "ExerciseFragment"
    }
}