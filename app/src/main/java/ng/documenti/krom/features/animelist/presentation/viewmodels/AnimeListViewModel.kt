package ng.documenti.krom.features.animelist.presentation.viewmodels

import android.util.Log
import ng.documenti.krom.features.animelist.domain.usecases.FetchAnimeUseCase
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ng.documenti.krom.common.Resource
import ng.documenti.krom.features.animelist.data.dataSources.JikanApiRating
import ng.documenti.krom.features.animelist.data.dataSources.topAnimeDTO.toAnimeModel
import ng.documenti.krom.features.animelist.domain.params.FetchTopAnimeParams
import ng.documenti.krom.features.animelist.states.AnimeListState
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val fetchAnimeUseCase: FetchAnimeUseCase
) : ViewModel(){

    private val _state = mutableStateOf(AnimeListState())

    val state: State<AnimeListState> = _state

    init {
        getTopAnime()
    }



    private  fun getTopAnime(){
        fetchAnimeUseCase(
            FetchTopAnimeParams()
        ).onEach { it ->
            when(it){
               is Resource.Success -> {
                   Log.d("TAG", "getTopAnime: Loaded data is ${it.data?.data ?: ""}")
                   _state.value = AnimeListState(animeList = it.data?.data?.map  {animeDto ->
                       animeDto.toAnimeModel()
                   }?: emptyList())
               }
               is Resource.Error -> {
                   Log.e("TAG", "getTopAnime: error is ${state.value}", )
                   _state.value = AnimeListState(error = it.message ?: "An unexpected error occurred")
               }
               is Resource.Loading -> {
                   Log.d("TAG", "getTopAnime:We are loading ")
                   _state.value = AnimeListState(isLoading = true)
               }
           }
        }.launchIn(viewModelScope)
    }
}