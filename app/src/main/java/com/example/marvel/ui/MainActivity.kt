package com.example.marvel.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.marvel.R
import com.example.marvel.repository.MarvelRepository

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MarvelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val marvelRepository = MarvelRepository()

        val viewModelProviderFactory = MarvelViewModelProviderFactory(marvelRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[MarvelViewModel::class.java]

    }
}