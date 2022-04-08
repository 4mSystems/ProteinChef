package app.te.protein_chef.core.di.module

import app.te.protein_chef.domain.account.repository.AccountRepository
import app.te.protein_chef.domain.account.use_case.AccountUseCases
import app.te.protein_chef.domain.account.use_case.CheckFirstTimeUseCase
import app.te.protein_chef.domain.account.use_case.CheckLoggedInUserUseCase
import app.te.protein_chef.domain.account.use_case.LogOutUseCase
import app.te.protein_chef.domain.account.use_case.UserLocalUseCase
import app.te.protein_chef.domain.account.use_case.SendFirebaseTokenUseCase
import app.te.protein_chef.domain.account.use_case.SetFirstTimeUseCase
import app.te.protein_chef.domain.auth.repository.AuthRepository
import app.te.protein_chef.domain.auth.use_case.ChangePasswordUseCase
import app.te.protein_chef.domain.auth.use_case.LogInUseCase
import app.te.protein_chef.domain.auth.use_case.RegisterUseCase
import app.te.protein_chef.domain.auth.use_case.VerifyAccountUseCase
import app.te.protein_chef.domain.general.use_case.LanguageUseCase
import app.te.protein_chef.domain.general.use_case.GeneralUseCases
import app.te.protein_chef.domain.home.repository.HomeRepository
import app.te.protein_chef.domain.home.use_case.HomeUseCase
import app.te.protein_chef.domain.intro.repository.IntroRepository
import app.te.protein_chef.domain.intro.use_case.IntroUseCase
import app.te.protein_chef.domain.make_order.repository.MakeOrderRepository
import app.te.protein_chef.domain.make_order.use_case.ApplyCouponUseCase
import app.te.protein_chef.domain.meals.repository.MealsRepository
import app.te.protein_chef.domain.meals.use_case.MealsUseCase
import app.te.protein_chef.domain.my_coupons.repository.MyCouponsRepository
import app.te.protein_chef.domain.my_coupons.use_case.MyCouponsUseCase
import app.te.protein_chef.domain.my_locations.repository.MyLocationsRepository
import app.te.protein_chef.domain.my_locations.use_case.MyLocationsUseCase
import app.te.protein_chef.domain.my_orders.repository.MyOrdersRepository
import app.te.protein_chef.domain.my_orders.use_case.CurrentOrdersUseCase
import app.te.protein_chef.domain.my_orders.use_case.PreviousOrdersUseCase
import app.te.protein_chef.domain.packages_categories.repository.PackageCategoriesRepository
import app.te.protein_chef.domain.packages_categories.use_case.PackageCategoriesUseCase
import app.te.protein_chef.domain.profile.repository.ProfileRepository
import app.te.protein_chef.domain.profile.use_case.ProfileUseCase
import app.te.protein_chef.domain.settings.repository.SettingsRepository
import app.te.protein_chef.domain.settings.use_case.OrderMainSettingsUseCase
import app.te.protein_chef.domain.settings.use_case.SettingsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

  @Provides
  @Singleton
  fun provideLogInUseCase(
    authRepository: AuthRepository,
    userLocalUseCase: UserLocalUseCase
  ): LogInUseCase = LogInUseCase(authRepository, userLocalUseCase)

  @Provides
  @Singleton
  fun provideRegisterUseCase(
    authRepository: AuthRepository
  ): RegisterUseCase = RegisterUseCase(authRepository)

  @Provides
  @Singleton
  fun provideVerifyAccountUseCase(
    authRepository: AuthRepository,
    userLocalUseCase: UserLocalUseCase
  ): VerifyAccountUseCase = VerifyAccountUseCase(authRepository, userLocalUseCase)

  @Provides
  @Singleton
  fun provideChangePasswordUseCase(
    authRepository: AuthRepository,
  ): ChangePasswordUseCase = ChangePasswordUseCase(authRepository)


  @Provides
  @Singleton
  fun provideHomeUseCase(
    homeRepository: HomeRepository
  ): HomeUseCase = HomeUseCase(homeRepository)


  @Provides
  @Singleton
  fun provideIntroUseCase(
    introRepository: IntroRepository
  ): IntroUseCase = IntroUseCase(introRepository)

  @Provides
  @Singleton
  fun provideSettingsUseCase(
    settingsRepository: SettingsRepository
  ): SettingsUseCase = SettingsUseCase(settingsRepository)


  @Provides
  @Singleton
  fun provideUpdateProfileUseCase(
    profileRepository: ProfileRepository,
    userLocalUseCase: UserLocalUseCase
  ): ProfileUseCase = ProfileUseCase(profileRepository, userLocalUseCase)

  @Provides
  @Singleton
  fun provideMyCouponsUseCase(
    myCouponsRepository: MyCouponsRepository
  ): MyCouponsUseCase = MyCouponsUseCase(myCouponsRepository)

  @Provides
  @Singleton
  fun provideMyLocationsUseCase(
    myLocationsRepository: MyLocationsRepository,
    userLocalUseCase: UserLocalUseCase
  ): MyLocationsUseCase = MyLocationsUseCase(myLocationsRepository, userLocalUseCase)

  @Provides
  @Singleton
  fun providePackageCategoriesUseCase(
    packageCategoriesRepository: PackageCategoriesRepository
  ): PackageCategoriesUseCase = PackageCategoriesUseCase(packageCategoriesRepository)

  @Provides
  @Singleton
  fun provideMealsUseCase(
    mealsRepository: MealsRepository
  ): MealsUseCase = MealsUseCase(mealsRepository)

  @Provides
  @Singleton
  fun provideApplyCouponUseCase(
    makeOrderRepository: MakeOrderRepository
  ): ApplyCouponUseCase = ApplyCouponUseCase(makeOrderRepository)

  @Provides
  @Singleton
  fun provideCurrentOrdersUseCase(
    myOrdersRepository: MyOrdersRepository
  ): CurrentOrdersUseCase = CurrentOrdersUseCase(myOrdersRepository)

  @Provides
  @Singleton
  fun providePreviousOrdersUseCase(
    myOrdersRepository: MyOrdersRepository
  ): PreviousOrdersUseCase = PreviousOrdersUseCase(myOrdersRepository)

  @Provides
  @Singleton
  fun provideOrderMainSettingsUseCase(
    settingsRepository: SettingsRepository,
    userLocalUseCase: UserLocalUseCase
  ): OrderMainSettingsUseCase = OrderMainSettingsUseCase(settingsRepository, userLocalUseCase)


  //public use cases
  @Provides
  @Singleton
  fun provideCheckFirstTimeUseCase(
    accountRepository: AccountRepository
  ): CheckFirstTimeUseCase = CheckFirstTimeUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideCheckLoggedInUserUseCase(
    accountRepository: AccountRepository
  ): CheckLoggedInUserUseCase = CheckLoggedInUserUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideSetFirstTimeUseCase(
    accountRepository: AccountRepository
  ): SetFirstTimeUseCase = SetFirstTimeUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideGeneralUseCases(
    checkFirstTimeUseCase: CheckFirstTimeUseCase,
    checkLoggedInUserUseCase: CheckLoggedInUserUseCase,
    setFirstTimeUseCase: SetFirstTimeUseCase,
    languageUseCase: LanguageUseCase
  ): GeneralUseCases =
    GeneralUseCases(
      checkFirstTimeUseCase,
      checkLoggedInUserUseCase,
      setFirstTimeUseCase,
      languageUseCase
    )

  @Provides
  @Singleton
  fun provideLogOutUseCase(
    accountRepository: AccountRepository
  ): LogOutUseCase = LogOutUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideSendFirebaseTokenUseCase(
    accountRepository: AccountRepository
  ): SendFirebaseTokenUseCase = SendFirebaseTokenUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideSaveUserToLocalUseCase(
    accountRepository: AccountRepository
  ): UserLocalUseCase = UserLocalUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideClearPreferencesUseCase(
    accountRepository: AccountRepository
  ): LanguageUseCase = LanguageUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideAccountUseCases(
    logOutUseCase: LogOutUseCase,
    sendFirebaseTokenUseCase: SendFirebaseTokenUseCase
  ): AccountUseCases = AccountUseCases(logOutUseCase, sendFirebaseTokenUseCase)

}