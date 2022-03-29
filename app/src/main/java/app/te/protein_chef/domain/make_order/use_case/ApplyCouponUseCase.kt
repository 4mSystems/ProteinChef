package app.te.protein_chef.domain.make_order.use_case

import app.te.protein_chef.domain.make_order.entity.coupon.ApplyCouponRequest
import app.te.protein_chef.domain.make_order.repository.MakeOrderRepository
import app.te.protein_chef.domain.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ApplyCouponUseCase @Inject constructor(
  private val makeOrderRepository: MakeOrderRepository
) {

  operator fun invoke(
    applyCouponRequest: ApplyCouponRequest
  ) = flow {
    emit(Resource.Loading)
    val result = makeOrderRepository.applyCoupon(applyCouponRequest)
    emit(result)
  }.flowOn(Dispatchers.IO)


}