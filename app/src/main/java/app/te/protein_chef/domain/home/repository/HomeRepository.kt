package app.te.protein_chef.domain.home.repository
import app.te.protein_chef.domain.home.models.HomeMainData
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource

interface HomeRepository {
  suspend fun getHome(lat: Double, lng: Double): Resource<BaseResponse<HomeMainData>>
}