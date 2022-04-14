package app.te.protein_chef.domain.my_orders.use_case

import app.te.protein_chef.domain.my_orders.entity.order_details.requests.CancelOrderRequest
import app.te.protein_chef.domain.my_orders.repository.MyOrdersRepository
import app.te.protein_chef.domain.utils.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class CancelOrderUseCase @Inject constructor(
  private val myOrdersRepository: MyOrdersRepository
) {
  operator fun invoke(cancelOrderRequest: CancelOrderRequest) = flow {
    emit(Resource.Loading)
    val result = myOrdersRepository.cancelOrder(cancelOrderRequest)
    emit(result)
  }


}