package app.te.protein_chef.domain.my_orders.use_case

import app.te.protein_chef.domain.my_orders.entity.order_details.requests.FreezeOrderRequest
import app.te.protein_chef.domain.my_orders.repository.MyOrdersRepository
import app.te.protein_chef.domain.utils.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class FreezeOrderUseCase @Inject constructor(
  private val myOrdersRepository: MyOrdersRepository
) {
  operator fun invoke(freezeOrderRequest: FreezeOrderRequest) = flow {
    emit(Resource.Loading)
    val result = myOrdersRepository.freezeOrder(freezeOrderRequest)
    emit(result)
  }
}