package ng.documenti.krom.features.uploads.data.repositories

import ng.documenti.krom.features.uploads.data.datasources.TraceApi
import ng.documenti.krom.features.uploads.data.datasources.searchWithImageDTO.SearchWithImageDTO
import okhttp3.MultipartBody
import javax.inject.Inject

class SearchWithImageRepositoryImpl @Inject constructor(
    private val searchWithImageApi: TraceApi
) : SearchWithImageRepository {
    override suspend fun searchWithImage(params: MultipartBody.Part): SearchWithImageDTO {
        return searchWithImageApi.searchUsingImage(params)
    }
}
