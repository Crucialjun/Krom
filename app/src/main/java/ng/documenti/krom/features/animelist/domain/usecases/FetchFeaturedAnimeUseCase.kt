package ng.documenti.krom.features.animelist.domain.usecases

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ng.documenti.krom.common.Resource
import ng.documenti.krom.core.usecases.NoParams
import ng.documenti.krom.core.usecases.UseCases
import ng.documenti.krom.features.animelist.data.dataSources.topAnimeDTO.toAnimeModel
import ng.documenti.krom.features.animelist.data.repositories.AnimeRepository
import ng.documenti.krom.features.animelist.domain.entities.AnimeModel
import javax.inject.Inject

class FetchFeaturedAnimeUseCase @Inject constructor(
        private val animeRepository : AnimeRepository
    ) : UseCases<AnimeModel, NoParams> {
        override  fun invoke(params: NoParams): Flow<Resource<AnimeModel>> {
            return  flow{

                emit(Resource.Loading())
                try{
                    val featuredAnime = animeRepository.getFeaturedAnime()
                    Log.d("TAG", "invoke:Received is ${featuredAnime.data} ")
                    emit(Resource.Success(featuredAnime.data.toAnimeModel()))
                }catch (e: Exception){
                    Log.e("Featured UseCase", "invoke error : ${e.toString()}", )
                    emit(Resource.Error(e.message ?: "An error occurred"))
                }

            }

        }
    }

