package app.te.protein_chef.domain.settings.use_case

import app.te.protein_chef.domain.account.use_case.UserLocalUseCase
import app.te.protein_chef.domain.settings.models.order_settings.OrderSettings
import app.te.protein_chef.domain.settings.repository.SettingsRepository
import app.te.protein_chef.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class OrderMainSettingsUseCase @Inject constructor(
  private val settingsRepository: SettingsRepository,
  private val userLocalUseCase: UserLocalUseCase
) {

  operator fun invoke() =
    flow {
      emit(Resource.Loading)
      val result = settingsRepository.orderCustomSettings()
      if (result is Resource.Success)
        saveDataToLocal(result.value.data)

      emit(result)
    }.flowOn(Dispatchers.IO)

  private suspend fun saveDataToLocal(data: OrderSettings) {
    userLocalUseCase.saveShippingValue(data.shipp_value)
    userLocalUseCase.saveWorkingHours(data.working_hours)
  }

}