package ng.documenti.krom.features.animelist.data.dataSources.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import ng.documenti.krom.features.animelist.domain.entities.AnimeModel

@Dao
interface AnimeDao {

    @Upsert
    suspend fun upsertAnime(anime: AnimeModel)

    @Delete
    suspend fun deleteAnime(anime: AnimeModel)

    @Query("SELECT * FROM AnimeModel")
    suspend fun  getAnimeList() : List<AnimeModel>

    @Query("DELETE FROM AnimeModel")
    suspend fun deleteAllAnimes()
}