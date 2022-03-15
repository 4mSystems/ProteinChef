package app.grand.tafwak.data.home.data_source.local

import androidx.room.*
import app.grand.tafwak.domain.home.models.HomeMainData
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeDao {
  @Query("Select * from HomeMainData")
  fun getNews(): Flow<HomeMainData>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertHomeData(homeMainData: HomeMainData)
  @Query("DELETE from HomeMainData")
  fun deleteHomeData()

}