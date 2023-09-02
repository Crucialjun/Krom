package ng.documenti.krom.features.uploads.data.datasources

import ng.documenti.krom.features.uploads.data.datasources.searchWithImageDTO.SearchWithImageDTO
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface TraceApi {
    @Multipart
    @POST("/search?anilistInfo")
    suspend fun searchUsingImage(@Part image: MultipartBody.Part) : SearchWithImageDTO
}