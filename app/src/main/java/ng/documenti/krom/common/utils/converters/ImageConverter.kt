package ng.documenti.krom.common.utils.converters

import androidx.room.TypeConverter
import ng.documenti.krom.features.animelist.data.dataSources.online.topAnimeDTO.Images
import ng.documenti.krom.features.animelist.data.dataSources.online.topAnimeDTO.Jpg
import ng.documenti.krom.features.animelist.data.dataSources.online.topAnimeDTO.Webp
import org.json.JSONObject


class ImageConverter {

    @TypeConverter
    fun fromImage(images: Images): String {
        return JSONObject().apply {
            put("jpg",images.jpg)
            put("webp",images.webp)
        }.toString()
    }

    @TypeConverter
    fun toImage(source :String) : Images {
        val jsonObject = JSONObject(source)
        return Images(
            jsonObject.get("jpg") as Jpg,
            jsonObject.get("webp") as Webp
        )
    }

}