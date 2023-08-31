package ng.documenti.krom.features.animelist.data.dataSources

import ng.documenti.krom.features.animelist.data.dataSources.topAnimeDTO.AnimeDto
import ng.documenti.krom.features.animelist.data.dataSources.topAnimeDTO.TopAnimeDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JikanApi {

    @GET("/v4/top/anime")
    suspend fun getTopAnime(
        @Query("type") type: String?,
        @Query("filter") filter: String?,
        @Query("rating") rating: Enum<JikanApiRating>?,
        @Query("sfw") sfw: Boolean?,
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
    ): TopAnimeDto

    @GET("/anime/{id}/full")
    suspend fun getAnime(
        @Query("id") id: Int,
    ): AnimeDetailsDto

}

enum class JikanApiRating {
    G, PG, PG13, R17, R, RX
}
