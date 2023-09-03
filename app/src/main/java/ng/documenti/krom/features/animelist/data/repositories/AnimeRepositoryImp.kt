package ng.documenti.krom.features.animelist.data.repositories

import ng.documenti.krom.features.animelist.data.dataSources.local.AnimeDao
import ng.documenti.krom.features.animelist.data.dataSources.online.AnimeDetailsDto
import ng.documenti.krom.features.animelist.data.dataSources.online.JikanApi
import ng.documenti.krom.features.animelist.data.dataSources.online.topAnimeDTO.toAnimeModel
import ng.documenti.krom.features.animelist.domain.entities.AnimeModel
import ng.documenti.krom.features.animelist.domain.params.FetchTopAnimeParams
import javax.inject.Inject


class AnimeRepositoryImp @Inject constructor(
    private val animeApi: JikanApi,
    private val dao : AnimeDao

    ) : AnimeRepository {
    override suspend fun getTopAnime(
       params: FetchTopAnimeParams,
       isOnline: Boolean
    ): List<AnimeModel> {
        if(isOnline){
            val response = animeApi.getTopAnime(
                    type = params.type,
                    filter = params.filter,
                    limit = params.limit,
                    page = params.page,
                    rating = params.rating,
                    sfw = params.sfw
                ).data

                  return   response.map {
                        it.toAnimeModel()
                    }



        }else{
            return dao.getAnimeList()


        }


    }

    override suspend fun getAnime(id: Int): AnimeDetailsDto {
        return  animeApi.getAnime(id)
    }

    override suspend fun getFeaturedAnime(): AnimeDetailsDto {
        return animeApi.getFeaturedAnime()
    }
}
