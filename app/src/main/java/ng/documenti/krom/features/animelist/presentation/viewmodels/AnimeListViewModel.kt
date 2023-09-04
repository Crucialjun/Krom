package ng.documenti.krom.features.animelist.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ng.documenti.krom.common.Resource
import ng.documenti.krom.core.usecases.NoParams
import ng.documenti.krom.features.animelist.data.dataSources.local.AnimeDao
import ng.documenti.krom.features.animelist.domain.params.FetchTopAnimeParams
import ng.documenti.krom.features.animelist.domain.usecases.FetchAnimeUseCase
import ng.documenti.krom.features.animelist.domain.usecases.FetchFeaturedAnimeUseCase
import ng.documenti.krom.features.animelist.states.AnimeListState
import ng.documenti.krom.features.animelist.states.FeaturedAnimeState
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val fetchAnimeUseCase: FetchAnimeUseCase,
    private val fetchFeaturedAnimeUseCase: FetchFeaturedAnimeUseCase,
    private val dao : AnimeDao
) : ViewModel(){

    private val _animeListState = mutableStateOf(AnimeListState())

    val animeListState: State<AnimeListState> = _animeListState

    private val _featuredAnimeState = mutableStateOf(FeaturedAnimeState())

    val featuredAnimeState: State<FeaturedAnimeState> = _featuredAnimeState


    init {
        getTopAnime()
        getFeaturedAnime()
    }

    suspend fun refreshList(){
        _animeListState.value = AnimeListState(isLoading = true)
        dao.deleteAllAnimes()
        getTopAnime()
    }



    private  fun getTopAnime(){
        fetchAnimeUseCase(
            FetchTopAnimeParams()

        ).onEach { it ->
            when(it){
               is Resource.Success -> {
                   _animeListState.value = AnimeListState(animeList = it.data ?: emptyList())
               }
               is Resource.Error -> {
                   Log.e("TAG", "getTopAnime: error is ${animeListState.value}", )
                   _animeListState.value = AnimeListState(error = it.message ?: "An unexpected error occurred")
               }
               is Resource.Loading -> {
                   Log.d("TAG", "getTopAnime:We are loading ")
                   _animeListState.value = AnimeListState(isLoading = true)
               }
           }
        }.launchIn(viewModelScope)


    }

    private fun getFeaturedAnime(){
        fetchFeaturedAnimeUseCase(NoParams()).onEach {
            when(it){
                is Resource.Success -> {
                    Log.d("TAG", "getFeaturedAnime: Loaded data is ${it.data}")
                    _featuredAnimeState.value = FeaturedAnimeState(featuredAnime = it.data)
                }
                is Resource.Error -> {
                    _featuredAnimeState.value = FeaturedAnimeState(error = it.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    Log.d("TAG", "getFeaturedAnime:We are loading ")
                    _featuredAnimeState.value = FeaturedAnimeState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }
}