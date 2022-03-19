package app.te.protein_chef.core.di.module

import android.content.Context
import app.te.protein_chef.data.local.preferences.AppPreferences
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppPreferencesModule {

  @Provides
  @Singleton
  fun providePreferences(@ApplicationContext context: Context) = AppPreferences(context)

  @Provides
  @Singleton
  fun provideFusedLocationProviderClient(@ApplicationContext context: Context): FusedLocationProviderClient {
    return LocationServices.getFusedLocationProviderClient(context)
  }
}