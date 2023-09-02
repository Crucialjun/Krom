package ng.documenti.krom.features.uploads.states

import ng.documenti.krom.features.uploads.data.datasources.searchWithImageDTO.SearchResult

data class SearchWithImageState(
    val searchWithImage: List<SearchResult>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""

)

