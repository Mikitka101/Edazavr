package com.nikitayasiulevich.edazavr.presentation.dishes

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikitayasiulevich.edazavr.domain.model.Restaurant

class DishesViewModelFactory(
    private val restaurant: String,
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DishesViewModel(restaurant, application) as T
    }
}