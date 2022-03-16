package app.te.protein_chef.domain.utils

import androidx.room.TypeConverter
import app.te.protein_chef.domain.home.models.Packages
import app.te.protein_chef.domain.home.models.Offers
import app.te.protein_chef.domain.home.models.Slider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
//    @TypeConverter
//  fun List<Classes>.toJsonString(): String = Gson().toJson(this)
//
//  @TypeConverter
//  fun String.toJsonModel(modelClass: List<Classes>): List<Classes> =
//    Gson().fromJson(this, modelClass)

  @TypeConverter
  fun fromClassesString(value: String): List<Packages> {
    val listType: Type = object : TypeToken<List<Packages>>() {}.type
    return Gson().fromJson(value, listType)
  }

  @TypeConverter
  fun fromClassesList(list: List<Packages>): String {
    val gson = Gson()
    return gson.toJson(list)
  }

  @TypeConverter
  fun fromInstructorString(value: String): List<Offers> {
    val listType: Type = object : TypeToken<List<Offers>>() {}.type
    return Gson().fromJson(value, listType)
  }

  @TypeConverter
  fun fromInstructorList(list: List<Offers>): String {
    val gson = Gson()
    return gson.toJson(list)
  }

  @TypeConverter
  fun fromSliderString(value: String): List<Slider> {
    val listType: Type = object : TypeToken<List<Slider>>() {}.type
    return Gson().fromJson(value, listType)
  }

  @TypeConverter
  fun fromSliderList(list: List<Slider>): String {
    val gson = Gson()
    return gson.toJson(list)
  }
}