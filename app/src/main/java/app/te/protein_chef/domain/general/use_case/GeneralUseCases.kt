package app.te.protein_chef.domain.general.use_case

import app.te.protein_chef.domain.account.use_case.CheckFirstTimeUseCase
import app.te.protein_chef.domain.account.use_case.CheckLoggedInUserUseCase
import app.te.protein_chef.domain.account.use_case.SetFirstTimeUseCase

class GeneralUseCases(
  val checkFirstTimeUseCase: CheckFirstTimeUseCase,
  val checkLoggedInUserUseCase: CheckLoggedInUserUseCase,
  val setFirstTimeUseCase: SetFirstTimeUseCase,
  val languageUseCase: LanguageUseCase
)