package ng.documenti.krom.features.animelist.domain.params

import ng.documenti.krom.features.animelist.data.dataSources.JikanApiRating

data class FetchTopAnimeParams(
    val type: String? = null,
    val filter: String? = null,
    val limit: Int? = null,
    val rating: Enum<JikanApiRating>? = null,
    val sfw: Boolean? = null ,
    val page: Int? = null
)