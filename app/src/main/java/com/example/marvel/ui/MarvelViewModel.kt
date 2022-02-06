package com.example.marvel.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel.models.MarvelResponse
import com.example.marvel.repository.MarvelRepository
import com.example.marvel.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MarvelViewModel(
    private val marvelRepository: MarvelRepository
): ViewModel() {

    val marvelCharacter: MutableLiveData<Resource<MarvelResponse>> = MutableLiveData()

     fun getCharacter(name: String, ts: Long, hash: String) = viewModelScope.launch {
        marvelCharacter.postValue(Resource.Loading())
        val response = marvelRepository.getCharacter(name, ts, hash)
        marvelCharacter.postValue(handleMarvelCharacterResponse(response))
    }

    fun handleMarvelCharacterResponse(response: Response<MarvelResponse>): Resource<MarvelResponse>{
        if(response.isSuccessful){
            response.body()?.let{ resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


}