package com.example.marvel.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.example.marvel.R
import com.example.marvel.ui.MainActivity
import com.example.marvel.ui.MarvelViewModel
import com.example.marvel.util.Constants.Companion.SEARCH_CHARACTER_TIME_DELAY
import com.example.marvel.util.Resource
import kotlinx.android.synthetic.main.fragment_character_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CharacterSearchFragment : Fragment() {

    private val TAG = "CharacterSearchFragment"
    lateinit var viewModel: MarvelViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        var job: Job? = null

        etCharacter.addTextChangedListener{ editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_CHARACTER_TIME_DELAY)
                editable?.let{
                    if(editable.toString().isNotEmpty()){
                        viewModel.getCharacter(editable.toString(), 1643981570L, "7298ebe3f6374e28705a6e36fd89b590")
                    }
                }
            }
        }

        viewModel.marvelCharacter.observe(viewLifecycleOwner, { response ->
            when(response){
                is Resource.Success -> {
                    response.data?.let{ marvelResponse ->
                        tvCharacterName.text = marvelResponse.data.results[0].name
                        tvCharacterDescription.text = marvelResponse.data.results[0].description
                        Glide.with(this@CharacterSearchFragment).load(
                            marvelResponse.data.results[0].thumbnail.path
                        ).into(ivCharacter)
                    }
                }

                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "onViewCreated: error occurred $message")
                    }
                }

                is Resource.Loading ->{

                }
            }
        })

    }


}