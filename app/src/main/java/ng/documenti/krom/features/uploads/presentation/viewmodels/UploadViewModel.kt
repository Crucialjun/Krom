package ng.documenti.krom.features.uploads.presentation.viewmodels

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ng.documenti.krom.common.Resource
import ng.documenti.krom.features.uploads.domain.SearchWithImageUseCase
import ng.documenti.krom.features.uploads.states.SearchWithImageState
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor(
    private val searchWithImageUseCase: SearchWithImageUseCase,
) : ViewModel(){

    var imageToSearch by mutableStateOf(Uri.EMPTY)
        private set

    fun updateImageToSearch(image: Uri) {
        this.imageToSearch = image
    }


    private val _searchWithImageState = mutableStateOf(SearchWithImageState())

    val searchWithImageState: State<SearchWithImageState> = _searchWithImageState




    fun searchWithImage(byteArray: ByteArray){

        searchWithImageUseCase(byteArray).onEach {
            when(it){
                is Resource.Success -> {
                    _searchWithImageState.value = SearchWithImageState(searchWithImage = it.data)
                    Log.d("TAG", "searchWithImage: ${it.data}")
                }
                is Resource.Error -> {
                    _searchWithImageState.value = SearchWithImageState(error = it.message ?: "An unexpected error occurred")
                    Log.e("TAG", "searchWithImage: ${it.message}", )
                }
                is Resource.Loading -> {
                    _searchWithImageState.value = SearchWithImageState(isLoading = true)
                    Log.d("TAG", "searchWithImage: Loading")
                }
            }
        }.launchIn(viewModelScope)




    }
}