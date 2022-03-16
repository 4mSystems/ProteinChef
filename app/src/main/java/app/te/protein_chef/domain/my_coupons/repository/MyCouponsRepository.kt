package app.te.protein_chef.domain.my_coupons.repository

import app.te.protein_chef.domain.my_coupons.entity.MyCouponsDto
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource

interface MyCouponsRepository {
  suspend fun getMyCoupons(): Resource<BaseResponse<List<MyCouponsDto>>>
}