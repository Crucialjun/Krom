package ng.documenti.krom.common.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import ng.documenti.krom.features.animelist.data.dataSources.online.topAnimeDTO.Images
import ng.documenti.krom.features.animelist.data.dataSources.online.topAnimeDTO.Jpg
import ng.documenti.krom.features.animelist.data.dataSources.online.topAnimeDTO.Webp
import org.json.JSONObject


class ImageConverter {

    @TypeConverter
    fun fromImage(images: Images): String {
        return JSONObject().apply {
            put("jpg", Gson ().toJson(images.jpg))
            put("webp",Gson ().toJson(images.webp))
        }.toString()
    }

    @TypeConverter
    fun toImage(source :String) : Images {
        val jsonObject = JSONObject(source)
        return Images(
            Gson().fromJson(jsonObject.get("jpg").toString(), Jpg::class.java),
            Gson().fromJson(jsonObject.get("webp").toString(), Webp::class.java)
        )
    }

}