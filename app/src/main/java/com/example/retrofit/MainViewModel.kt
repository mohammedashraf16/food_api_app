package com.example.retrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.model.MealsResponse
import com.example.retrofit.data.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _meals = MutableStateFlow<MealsResponse?>(null)
    val meals: StateFlow<MealsResponse?> get() = _meals
    val api = RetrofitInstance.apiClient
    fun getMeals() {
        viewModelScope.launch {
            val response = api.getMeals()
            if (response.isSuccessful){
                _meals.value=response.body()
            }
        }
    }
}