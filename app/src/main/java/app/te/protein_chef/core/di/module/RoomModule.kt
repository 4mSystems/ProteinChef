package app.te.protein_chef.core.di.module

import android.content.Context
import androidx.room.Room
import app.te.protein_chef.core.AppDatabase
import app.te.protein_chef.data.home.data_source.local.HomeLocalRemoteDataSource
import app.te.protein_chef.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
  @Provides
  @Singleton
  fun provideMyDB(@ApplicationContext context: Context): AppDatabase {
    return Room.databaseBuilder(
      context,
      AppDatabase::class.java,
      BuildConfig.ROOM_DB
    ).build()
  }

  @Provides
  @Singleton
  fun provideHomeLocalRepository(db: AppDatabase): HomeLocalRemoteDataSource {
    return HomeLocalRemoteDataSource(db.getHomeDao)
  }

}



