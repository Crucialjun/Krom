package ng.documenti.krom.features.animelist.domain.entities

data class Webp(
    @JsonProperty("image_url")
    val imageUrl: String,
    @JsonProperty("small_image_url")
    val smallImageUrl: String,
    @JsonProperty("large_image_url")
    val largeImageUrl: String,
)