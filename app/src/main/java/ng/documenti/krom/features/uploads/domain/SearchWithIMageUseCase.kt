package ng.documenti.krom.features.uploads.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ng.documenti.krom.common.Resource
import ng.documenti.krom.core.usecases.UseCases
import ng.documenti.krom.features.uploads.data.datasources.searchWithImageDTO.SearchResult
import ng.documenti.krom.features.uploads.data.repositories.SearchWithImageRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class SearchWithImageUseCase @Inject constructor(
    private val searchWithImageRepository: SearchWithImageRepository
) : UseCases <List<SearchResult>, ByteArray>{
    override fun invoke(params: ByteArray): Flow<Resource<List<SearchResult>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val image = MultipartBody.Part.createFormData(
                    "image", "image", RequestBody.create(
                        "image/*".toMediaTypeOrNull(), params
                    )
                )
                val response = searchWithImageRepository.searchWithImage(image)
                emit(Resource.Success(response.result))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "An error occurred"))
            }
        }
    }

}