package com.example.moviesapp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.remote.CharactersService
import com.example.moviesapp.data.remote.dto.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    var state: StateFlow<UiState> = _state

    init {
        viewModelScope.launch {
            _state.value = UiState(
                Retrofit.Builder()
                    .baseUrl("https://gateway.marvel.com/v1/public/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(CharactersService::class.java)
                    .getCharacters(
                        "1",
                        "7516e0fd51ddafea921d6f2683998946",
                        "d33501019f760a552e172d7fb9b73b2d"
                    ).data.results
            )
        }
    }

    data class UiState(val characters: List<Result> = emptyList())
}