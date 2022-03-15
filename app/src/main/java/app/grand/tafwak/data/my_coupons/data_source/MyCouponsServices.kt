package app.grand.tafwak.data.my_coupons.data_source

import app.grand.tafwak.domain.my_coupons.entity.MyCouponsDto
import app.grand.tafwak.domain.utils.BaseResponse
import retrofit2.http.*

interface MyCouponsServices {

  @GET("V1/user/coupons")
  suspend fun myCoupons(): BaseResponse<List<MyCouponsDto>>

}