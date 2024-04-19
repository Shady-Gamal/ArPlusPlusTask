package com.example.data.data_sources.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.data.models.SourceModel
import com.example.data.util.JsonParser
import com.google.gson.reflect.TypeToken


@ProvidedTypeConverter
class Converters(val jsonParser: JsonParser) {

    @TypeConverter
    fun fromSource(json: String) : SourceModel{
        return jsonParser.fromJson<SourceModel>(
            json = json,
            object : TypeToken<SourceModel>(){}.type
        ) ?: SourceModel()
    }
    @TypeConverter
    fun toSource(source :SourceModel?) : String{
        return jsonParser.toJson(
            source,
            object : TypeToken<SourceModel>(){}.type
        )?: "[]"
    }

}