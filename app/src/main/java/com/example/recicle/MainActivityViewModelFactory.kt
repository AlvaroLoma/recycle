package com.example.recicle

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import data.Repository

class MainActivityViewModelFactory(val repository: Repository, val application: Application): ViewModelProvider.Factory {

    @SuppressWarnings("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MainActivityViewModel(repository,application) as T



}