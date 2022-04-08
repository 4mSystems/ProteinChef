package app.te.protein_chef.core.di.module

import app.te.protein_chef.data.account.data_source.remote.AccountRemoteDataSource
import app.te.protein_chef.data.account.repository.AccountRepositoryImpl
import app.te.protein_chef.data.auth.data_source.remote.AuthRemoteDataSource
import app.te.protein_chef.data.auth.repository.AuthRepositoryImpl
import app.te.protein_chef.data.general.data_source.remote.GeneralRemoteDataSource
import app.te.protein_chef.data.general.repository.GeneralRepositoryImpl
import app.te.protein_chef.data.home.data_source.local.HomeLocalRemoteDataSource
import app.te.protein_chef.data.home.repository.local.HomeLocalRepositoryImpl
import app.te.protein_chef.data.home.data_source.remote.HomeRemoteDataSource
import app.te.protein_chef.data.home.repository.HomeRepositoryImpl
import app.te.protein_chef.data.intro.data_source.IntroRemoteDataSource
import app.te.protein_chef.data.intro.repository.IntroRepositoryImpl
import app.te.protein_chef.data.local.preferences.AppPreferences
import app.te.protein_chef.data.make_order.data_source.MakeOrderDataSource
import app.te.protein_chef.data.make_order.repository.MakeOrderRepositoryImpl
import app.te.protein_chef.data.meals.data_source.MealsDataSource
import app.te.protein_chef.data.meals.repository.MealsRepositoryImpl
import app.te.protein_chef.data.my_coupons.data_source.MyCouponsDataSource
import app.te.protein_chef.data.my_coupons.repository.MyCouponsRepositoryImpl
import app.te.protein_chef.data.my_locations.data_source.MyLocationsDataSource
import app.te.protein_chef.data.my_locations.repository.MyLocationsRepositoryImpl
import app.te.protein_chef.data.my_order.data_source.MyOrdersDataSource
import app.te.protein_chef.data.my_order.repository.MyOrdersRepositoryImpl
import app.te.protein_chef.data.package_categories.data_source.PackageCategoriesDataSource
import app.te.protein_chef.data.package_categories.repository.PackageCategoriesRepositoryImpl
import app.te.protein_chef.data.profile.data_source.ProfileDataSource
import app.te.protein_chef.data.profile.repository.ProfileRepositoryImpl
import app.te.protein_chef.data.settings.data_source.remote.SettingsRemoteDataSource
import app.te.protein_chef.data.settings.repository.SettingsRepositoryImpl
import app.te.protein_chef.domain.account.repository.AccountRepository
import app.te.protein_chef.domain.auth.repository.AuthRepository
import app.te.protein_chef.domain.general.repository.GeneralRepository
import app.te.protein_chef.domain.home.repository.HomeRepository
import app.te.protein_chef.domain.home.repository.local.HomeLocalRepository
import app.te.protein_chef.domain.intro.repository.IntroRepository
import app.te.protein_chef.domain.make_order.repository.MakeOrderRepository
import app.te.protein_chef.domain.meals.repository.MealsRepository
import app.te.protein_chef.domain.my_coupons.repository.MyCouponsRepository
import app.te.protein_chef.domain.my_locations.repository.MyLocationsRepository
import app.te.protein_chef.domain.my_orders.repository.MyOrdersRepository
import app.te.protein_chef.domain.packages_categories.repository.PackageCategoriesRepository
import app.te.protein_chef.domain.profile.repository.ProfileRepository
import app.te.protein_chef.domain.settings.repository.SettingsRepository
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

  @Provides
  @Singleton
  fun provideMakeOrderRepository(
    remoteDataSource: MakeOrderDataSource
  ): MakeOrderRepository = MakeOrderRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideMyOrdersRepository(
    remoteDataSource: MyOrdersDataSource
  ): MyOrdersRepository = MyOrdersRepositoryImpl(remoteDataSource)


}