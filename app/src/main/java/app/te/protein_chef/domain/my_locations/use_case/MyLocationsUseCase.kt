package app.te.protein_chef.domain.my_locations.use_case

import app.te.protein_chef.domain.account.use_case.UserLocalUseCase
import app.te.protein_chef.domain.my_locations.repository.MyLocationsRepository
import app.te.protein_chef.domain.utils.*
import app.te.protein_chef.presentation.my_locations.ui_state.MyLocationsDataUiState
import app.te.protein_chef.presentation.my_locations.ui_state.MyLocationsEmptyUiState
import app.te.protein_chef.presentation.my_locations.ui_state.MyLocationsUiState
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


class MyLocationsUseCase @Inject constructor(
  private val myLocationsRepository: MyLocationsRepository,
  private val userLocalUseCase: UserLocalUseCase
) {
  var defaultId = 0

  operator fun invoke() = flow {
    getId()
    emit(Resource.Loading)
    val result = myLocationsRepository.getMyLocations()
    val items = mutableListOf<MyLocationsUiState>()
    if (result is Resource.Success) {
      val data = result.value.data
      if (data.isNullOrEmpty()) {
        items.add(MyLocationsEmptyUiState())
        emit(Resource.Success(items))
      } else {
        data.map { locationDto ->
          if (locationDto.id == defaultId)
            locationDto.isDefault = true
          items.add(MyLocationsDataUiState(locationDto))
        }
        emit(Resource.Success(items))
      }

    } else
      emit(result)
  }.flowOn(Dispatchers.IO)

  @OptIn(DelicateCoroutinesApi::class)
  fun getId() {
    GlobalScope.launch {
      userLocalUseCase.getDefaultLocation().flowOn(Dispatchers.IO)
        .collectLatest { defaultLocation ->
          defaultId = defaultLocation.id
        }
    }
  }

  fun removeLocation(locationId: Int) = flow {
    emit(Resource.Loading)
    val result = myLocationsRepository.removeLocation(locationId)
    emit(result)
  }.flowOn(Dispatchers.IO)

}