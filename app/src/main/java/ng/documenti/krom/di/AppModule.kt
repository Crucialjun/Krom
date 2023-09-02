package ng.documenti.krom.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ng.documenti.krom.common.Constants
import ng.documenti.krom.features.animelist.data.dataSources.JikanApi
import ng.documenti.krom.features.animelist.data.repositories.AnimeRepository
import ng.documenti.krom.features.animelist.data.repositories.AnimeRepositoryImp
import ng.documenti.krom.features.uploads.data.datasources.TraceApi
import ng.documenti.krom.features.uploads.data.repositories.SearchWithImageRepository
import ng.documenti.krom.features.uploads.data.repositories.SearchWithImageRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideJikanApi(): JikanApi {
        return Retrofit.Builder()
            .baseUrl(Constants.JIKAN_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

            .build()
            .create(JikanApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAnimeListRepository(
        api: JikanApi
    ): AnimeRepository {
        return AnimeRepositoryImp(api)
    }


    @Provides
    @Singleton
    fun provideSearchWithImageApi() : TraceApi {
        return Retrofit.Builder()
            .baseUrl(Constants.TRACE_MOE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TraceApi::class.java)
    }


    @Provides
    @Singleton
    fun provideSearchWithImageRepository(
        api: TraceApi
    ) : SearchWithImageRepository {
        return SearchWithImageRepositoryImpl(api)
    }



}