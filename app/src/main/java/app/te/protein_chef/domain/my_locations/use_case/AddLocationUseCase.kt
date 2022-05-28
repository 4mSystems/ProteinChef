package app.te.protein_chef.domain.my_locations.use_case

import app.te.protein_chef.domain.my_locations.entity.MyLocationDto
import app.te.protein_chef.domain.my_locations.entity.request.AddLocationRequest
import app.te.protein_chef.domain.my_locations.repository.MyLocationsRepository
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.presentation.base.utils.Constants
import app.te.protein_chef.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class AddLocationUseCase @Inject constructor(
  private val myLocationsRepository: MyLocationsRepository,
) {

  operator fun invoke(request: AddLocationRequest): Flow<Resource<BaseResponse<MyLocationDto>>> =
    flow {
      if (checkValidation(request)) {
        emit(Resource.Loading)
        val result = myLocationsRepository.addLocation(request)
        emit(result)
      }
    }.flowOn(Dispatchers.IO)

  fun checkValidation(request: AddLocationRequest): Boolean {
    var isValid = true
    if (request.title.isEmpty()) {
      request.validation.titleError.set(Constants.EMPTY)
      isValid = false
    }
    if (request.bulding_num.isEmpty()) {
      request.validation.buildingError.set(Constants.EMPTY)
      isValid = false
    }
    if (request.flat_num.isEmpty()) {
      request.validation.flatError.set(Constants.EMPTY)
      isValid = false
    }
    if (request.notes.isEmpty()) {
      request.validation.noteError.set(Constants.EMPTY)
      isValid = false
    }

    return isValid
  }
}