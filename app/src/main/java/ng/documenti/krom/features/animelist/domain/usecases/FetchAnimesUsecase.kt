package ng.documenti.krom.features.animelist.domain.usecases


import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ng.documenti.krom.common.Resource
import ng.documenti.krom.core.usecases.UseCases
import ng.documenti.krom.features.animelist.data.dataSources.topAnimeDTO.TopAnimeDto
import ng.documenti.krom.features.animelist.data.repositories.AnimeRepository
import ng.documenti.krom.features.animelist.domain.params.FetchTopAnimeParams
import javax.inject.Inject

class FetchAnimeUseCase @Inject constructor(
    private val animeRepository : AnimeRepository
) : UseCases<TopAnimeDto, FetchTopAnimeParams> {
    override  fun invoke(params: FetchTopAnimeParams): Flow<Resource<TopAnimeDto>> {
        return  flow{

            emit(Resource.Loading())
            try{
                val topAnime = animeRepository.getTopAnime(params)

                emit(Resource.Success(topAnime))
            }catch (e: Exception){
                emit(Resource.Error(e.message ?: "An error occurred"))
            }

        }

    }
}


