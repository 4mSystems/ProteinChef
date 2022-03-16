package app.te.protein_chef.domain.settings.use_case

import app.te.protein_chef.domain.settings.models.SettingsData
import app.te.protein_chef.domain.settings.repository.SettingsRepository
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class SettingsUseCase @Inject constructor(
  private val settingsRepository: SettingsRepository
) {

  operator fun invoke(
    type: String
  ): Flow<Resource<BaseResponse<SettingsData>>> =
    flow {
      emit(Resource.Loading)
      val result = settingsRepository.settings(type)
      emit(result)
//      if (result is Resource.Success)
//        emit(
//          Resource.Success(
//            BaseResponse(
//              data = SettingsDataUiState(result.value.data.body),
//              message = result.value.message,
//              status = result.value.status
//            )
//          )
//        )
//
    }.flowOn(Dispatchers.IO)


}