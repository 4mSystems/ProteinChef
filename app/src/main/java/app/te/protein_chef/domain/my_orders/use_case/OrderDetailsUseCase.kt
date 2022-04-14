package app.te.protein_chef.domain.my_orders.use_case

import app.te.protein_chef.domain.my_orders.entity.order_details.OrderDetails
import app.te.protein_chef.domain.my_orders.repository.MyOrdersRepository
import app.te.protein_chef.domain.utils.*
import app.te.protein_chef.presentation.order_details.ui_state.OrderDetailsUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class OrderDetailsUseCase @Inject constructor(
  private val myOrdersRepository: MyOrdersRepository
) {
  operator fun invoke(orderId: Int) = flow {
    emit(Resource.Loading)
    val result = myOrdersRepository.getOrderDetails(orderId)
    if (result is Resource.Success) {
      emit(Resource.Success(updateOrderDetails(result.value.data)))
    } else
      emit(result)
  }.flowOn(Dispatchers.IO)

  private fun updateOrderDetails(orderDetails: OrderDetails): OrderDetailsUiState {
    return OrderDetailsUiState(
      discount_price = orderDetails.discount_price,
      frozen_meals = orderDetails.frozen_meals,
      location = orderDetails.location,
      company_address = orderDetails.company_address.value,
      meal_types = orderDetails.meal_types,
      order_addition_prices = orderDetails.order_addition_prices,
      order_meals = orderDetails.order_meals,
      package_price = orderDetails.package_price,
      remaining_days = orderDetails.remaining_days,
      shipping_price = orderDetails.shipping_price,
      total_price = orderDetails.total_price,
      working_hours = orderDetails.working_hours,
      remain_frozen_meals = orderDetails.remain_frozen_meals,
      package_name = orderDetails.package_name,
      order_status = orderDetails.order_status,
    )
  }

  operator fun invoke(orderId: Int, categoryId: Int) = flow {
    emit(Resource.Loading)
    val result = myOrdersRepository.getOrderMealsByCategoryId(orderId, categoryId)
    if (result is Resource.Success) {
      emit(Resource.Success(orderMealsUiState(result.value.data)))
    } else
      emit(result)
  }.flowOn(Dispatchers.IO)

  private fun orderMealsUiState(orderDetails: OrderDetails): OrderDetailsUiState {
    return OrderDetailsUiState(
      order_meals = orderDetails.order_meals,
    )
  }

}