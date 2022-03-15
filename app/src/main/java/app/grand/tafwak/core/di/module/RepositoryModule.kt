package app.grand.tafwak.core.di.module

import app.grand.tafwak.data.account.data_source.remote.AccountRemoteDataSource
import app.grand.tafwak.data.account.repository.AccountRepositoryImpl
import app.grand.tafwak.data.auth.data_source.remote.AuthRemoteDataSource
import app.grand.tafwak.data.auth.repository.AuthRepositoryImpl
import app.grand.tafwak.data.general.data_source.remote.GeneralRemoteDataSource
import app.grand.tafwak.data.general.repository.GeneralRepositoryImpl
import app.grand.tafwak.data.home.data_source.local.HomeLocalRemoteDataSource
import app.grand.tafwak.data.home.repository.local.HomeLocalRepositoryImpl
import app.grand.tafwak.data.home.data_source.remote.HomeRemoteDataSource
import app.grand.tafwak.data.home.repository.HomeRepositoryImpl
import app.grand.tafwak.data.intro.data_source.IntroRemoteDataSource
import app.grand.tafwak.data.intro.repository.IntroRepositoryImpl
import app.grand.tafwak.data.local.preferences.AppPreferences
import app.grand.tafwak.data.meals.data_source.MealsDataSource
import app.grand.tafwak.data.meals.repository.MealsRepositoryImpl
import app.grand.tafwak.data.my_coupons.data_source.MyCouponsDataSource
import app.grand.tafwak.data.my_coupons.repository.MyCouponsRepositoryImpl
import app.grand.tafwak.data.my_locations.data_source.MyLocationsDataSource
import app.grand.tafwak.data.my_locations.repository.MyLocationsRepositoryImpl
import app.grand.tafwak.data.package_categories.data_source.PackageCategoriesDataSource
import app.grand.tafwak.data.package_categories.repository.PackageCategoriesRepositoryImpl
import app.grand.tafwak.data.profile.data_source.ProfileDataSource
import app.grand.tafwak.data.profile.repository.ProfileRepositoryImpl
import app.grand.tafwak.data.settings.data_source.remote.SettingsRemoteDataSource
import app.grand.tafwak.data.settings.repository.SettingsRepositoryImpl
import app.grand.tafwak.domain.account.repository.AccountRepository
import app.grand.tafwak.domain.auth.repository.AuthRepository
import app.grand.tafwak.domain.general.repository.GeneralRepository
import app.grand.tafwak.domain.home.repository.HomeRepository
import app.grand.tafwak.domain.home.repository.local.HomeLocalRepository
import app.grand.tafwak.domain.intro.repository.IntroRepository
import app.grand.tafwak.domain.meals.repository.MealsRepository
import app.grand.tafwak.domain.my_coupons.repository.MyCouponsRepository
import app.grand.tafwak.domain.my_locations.repository.MyLocationsRepository
import app.grand.tafwak.domain.packages_categories.repository.PackageCategoriesRepository
import app.grand.tafwak.domain.profile.repository.ProfileRepository
import app.grand.tafwak.domain.settings.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

  @Provides
  @Singleton
  fun provideGeneralRepository(
    remoteDataSource: GeneralRemoteDataSource,
    appPreferences: AppPreferences
  ): GeneralRepository = GeneralRepositoryImpl(remoteDataSource, appPreferences)

  @Provides
  @Singleton
  fun provideAuthRepository(
    remoteDataSource: AuthRemoteDataSource,
  ): AuthRepository = AuthRepositoryImpl(remoteDataSource)


  @Provides
  @Singleton
  fun provideAccountRepository(
    remoteDataSource: AccountRemoteDataSource,
    appPreferences: AppPreferences
  ): AccountRepository = AccountRepositoryImpl(remoteDataSource, appPreferences)

  @Provides
  @Singleton
  fun provideSettingsRepository(
    remoteDataSource: SettingsRemoteDataSource
  ): SettingsRepository = SettingsRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideHomeRepository(
    remoteDataSource: HomeRemoteDataSource
  ): HomeRepository = HomeRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideHomeLocalRepository(
    homeLocalRemoteDataSource: HomeLocalRemoteDataSource
  ): HomeLocalRepository = HomeLocalRepositoryImpl(homeLocalRemoteDataSource)

  @Provides
  @Singleton
  fun provideIntroRepository(
    remoteDataSource: IntroRemoteDataSource
  ): IntroRepository = IntroRepositoryImpl(remoteDataSource)


  @Provides
  @Singleton
  fun provideUpdateProfileRepository(
    remoteDataSource: ProfileDataSource
  ): ProfileRepository = ProfileRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideMyCouponsRepository(
    remoteDataSource: MyCouponsDataSource
  ): MyCouponsRepository = MyCouponsRepositoryImpl(remoteDataSource)


  @Provides
  @Singleton
  fun provideMyLocationsRepository(
    remoteDataSource: MyLocationsDataSource
  ): MyLocationsRepository = MyLocationsRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun providePackageCategoriesRepository(
    remoteDataSource: PackageCategoriesDataSource
  ): PackageCategoriesRepository = PackageCategoriesRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideMealsRepository(
    remoteDataSource: MealsDataSource
  ): MealsRepository = MealsRepositoryImpl(remoteDataSource)


}