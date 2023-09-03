package ng.documenti.krom.features.animelist.domain.usecases


import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ng.documenti.krom.common.Resource
import ng.documenti.krom.core.usecases.UseCases
import ng.documenti.krom.features.animelist.data.repositories.AnimeRepository
import ng.documenti.krom.features.animelist.domain.entities.AnimeModel
import ng.documenti.krom.features.animelist.domain.params.FetchTopAnimeParams
import javax.inject.Inject

class FetchAnimeUseCase @Inject constructor(
    private val animeRepository : AnimeRepository
) : UseCases <List<AnimeModel>, FetchTopAnimeParams> {
    override  fun invoke(params: FetchTopAnimeParams): Flow<Resource<List<AnimeModel>>> {
        return  flow{
            emit(Resource.Loading())
            try{
                val topAnime = animeRepository.getTopAnime(params,true)
                Log.d("Fetch animes usecase", "invoke: received ${topAnime.size} animes ")
                emit(Resource.Success(topAnime))
            }catch (e: Exception){
                Log.e("Fetch animes usecase", "invoke: error is ${e.toString()}", )
                emit(Resource.Error(e.message ?: "An error occurred"))
            }

        }

    }
}


