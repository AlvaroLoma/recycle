package com.example.recicle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import data.Repository

class MainActivityViewModelFactory(val repository: Repository): ViewModelProvider.Factory {

    @SuppressWarnings("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MainActivityViewModel(repository) as T



}