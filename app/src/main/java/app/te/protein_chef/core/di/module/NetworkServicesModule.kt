package app.te.protein_chef.core.di.module

import app.te.protein_chef.data.account.data_source.remote.AccountServices
import app.te.protein_chef.data.auth.data_source.remote.AuthServices
import app.te.protein_chef.data.general.data_source.remote.GeneralServices
import app.te.protein_chef.data.home.data_source.remote.HomeServices
import app.te.protein_chef.data.intro.data_source.IntroServices
import app.te.protein_chef.data.meals.data_source.MealsServices
import app.te.protein_chef.data.my_coupons.data_source.MyCouponsServices
import app.te.protein_chef.data.my_locations.data_source.MyLocationsServices
import app.te.protein_chef.data.package_categories.data_source.PackageCategoriesServices
import app.te.protein_chef.data.profile.data_source.ProfileServices
import app.te.protein_chef.data.settings.data_source.remote.SettingsServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkServicesModule {

  @Provides
  @Singleton
  fun provideAuthServices(retrofit: Retrofit): AuthServices =
    retrofit.create(AuthServices::class.java)

  @Provides
  @Singleton
  fun provideAccountServices(retrofit: Retrofit): AccountServices =
    retrofit.create(AccountServices::class.java)

  @Provides
  @Singleton
  fun provideGeneralServices(retrofit: Retrofit): GeneralServices =
    retrofit.create(GeneralServices::class.java)

  @Provides
  @Singleton
  fun provideSearchServices(retrofit: Retrofit): SettingsServices =
    retrofit.create(SettingsServices::class.java)

  @Provides
  @Singleton
  fun provideHomeServices(retrofit: Retrofit): HomeServices =
    retrofit.create(HomeServices::class.java)

  @Provides
  @Singleton
  fun provideIntroServices(retrofit: Retrofit): IntroServices =
    retrofit.create(IntroServices::class.java)


  @Provides
  @Singleton
  fun provideUpdateProfileServices(retrofit: Retrofit): ProfileServices =
    retrofit.create(ProfileServices::class.java)

  @Provides
  @Singleton
  fun provideMyCouponsServices(retrofit: Retrofit): MyCouponsServices =
    retrofit.create(MyCouponsServices::class.java)

  @Provides
  @Singleton
  fun provideMyLocationsServices(retrofit: Retrofit): MyLocationsServices =
    retrofit.create(MyLocationsServices::class.java)

  @Provides
  @Singleton
  fun providePackageCategoriesServices(retrofit: Retrofit): PackageCategoriesServices =
    retrofit.create(PackageCategoriesServices::class.java)

  @Provides
  @Singleton
  fun provideMealsServices(retrofit: Retrofit): MealsServices =
    retrofit.create(MealsServices::class.java)


}