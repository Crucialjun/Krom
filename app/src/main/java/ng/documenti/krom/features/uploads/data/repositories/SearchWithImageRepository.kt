package ng.documenti.krom.features.uploads.data.repositories

import ng.documenti.krom.features.uploads.data.datasources.searchWithImageDTO.SearchWithImageDTO
import okhttp3.MultipartBody

interface SearchWithImageRepository {

    suspend fun searchWithImage(
            params: MultipartBody.Part
        ) : SearchWithImageDTO





}