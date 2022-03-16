package app.te.protein_chef.data.my_coupons.data_source

import app.te.protein_chef.domain.my_coupons.entity.MyCouponsDto
import app.te.protein_chef.domain.utils.BaseResponse
import retrofit2.http.*

interface MyCouponsServices {

  @GET("V1/user/coupons")
  suspend fun myCoupons(): BaseResponse<List<MyCouponsDto>>

}