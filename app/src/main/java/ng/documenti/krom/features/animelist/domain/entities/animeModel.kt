package ng.documenti.krom.features.animelist.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ng.documenti.krom.features.animelist.data.dataSources.online.topAnimeDTO.Images

@Entity
data class AnimeModel(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title: String,
    val images: Images,
    val duration : String,
    val year : Int
) {

}