package app.grand.tafwak.domain.home.use_case

import app.grand.tafwak.domain.home.repository.HomeRepository
import app.grand.tafwak.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class HomeUseCase @Inject constructor(
  private val homeRepository: HomeRepository) {
  fun homeService(lat: Double, lng: Double) = flow {
    emit(Resource.Loading)
    val result = homeRepository.getHome(lat, lng)
    emit(result)
  }.flowOn(Dispatchers.IO)

}
