package com.continuouswave1550.fitness.fitnessprohome.nutrition

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class NutritionViewModel @Inject constructor() : ViewModel() {

    private val _mealLiveData = MutableLiveData<Meal>()
    val mealLiveData: LiveData<Meal>
        get() = _mealLiveData

    fun onSetMeal(_mealName: String,
                    _mealDescription: String) {
        val meal = Meal()
        meal.mealName = _mealName
        meal.mealDescription = _mealDescription
        _mealLiveData.value = meal
    }

    companion object {
        const val TAG = "NutritionViewModel"
    }
}

data class Meal(
    var mealId: Long = 0L,
    var mealName: String = "",
    var mealDescription: String = ""
)