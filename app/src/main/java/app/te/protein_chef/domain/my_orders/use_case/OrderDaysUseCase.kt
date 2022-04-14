package app.te.protein_chef.domain.my_orders.use_case

import app.te.protein_chef.domain.my_orders.repository.MyOrdersRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class OrderDaysUseCase @Inject constructor(
  private val myOrdersRepository: MyOrdersRepository
) {
  operator fun invoke(orderId: Int) = flow {
    val result = myOrdersRepository.orderDays(orderId)
    emit(result)
  }


}