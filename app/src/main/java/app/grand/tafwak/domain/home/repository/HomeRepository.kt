package app.grand.tafwak.domain.home.repository
import app.grand.tafwak.domain.home.models.HomeMainData
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource

interface HomeRepository {
  suspend fun getHome(lat: Double, lng: Double): Resource<BaseResponse<HomeMainData>>
}