package app.te.protein_chef.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.te.protein_chef.data.home.data_source.local.HomeDao
import app.te.protein_chef.domain.home.models.HomeMainData
import app.te.protein_chef.domain.utils.Converters

@Database(
  entities = [HomeMainData::class],
  version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
  abstract val getHomeDao: HomeDao
}