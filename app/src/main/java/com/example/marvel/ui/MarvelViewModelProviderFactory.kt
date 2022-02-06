package com.example.marvel.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marvel.repository.MarvelRepository

class MarvelViewModelProviderFactory(
    private val marvelRepository: MarvelRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MarvelViewModel(marvelRepository) as T
    }

}