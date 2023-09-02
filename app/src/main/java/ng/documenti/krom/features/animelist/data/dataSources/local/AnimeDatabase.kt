package ng.documenti.krom.features.animelist.data.dataSources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ng.documenti.krom.common.utils.converters.ImageConverter
import ng.documenti.krom.features.animelist.domain.entities.AnimeModel

@Database(entities = [AnimeModel::class], version = 1)
@TypeConverters(ImageConverter::class)
abstract class AnimeDatabase : RoomDatabase(){
    abstract fun animeDao() : AnimeDao


}