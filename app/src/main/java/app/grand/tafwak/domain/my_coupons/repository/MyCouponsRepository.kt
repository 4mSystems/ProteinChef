package app.grand.tafwak.domain.my_coupons.repository

import app.grand.tafwak.domain.my_coupons.entity.MyCouponsDto
import app.grand.tafwak.domain.utils.BaseResponse
import app.grand.tafwak.domain.utils.Resource

interface MyCouponsRepository {
  suspend fun getMyCoupons(): Resource<BaseResponse<List<MyCouponsDto>>>
}