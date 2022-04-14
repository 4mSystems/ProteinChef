package app.te.protein_chef.data.my_order.data_source

import app.te.protein_chef.data.remote.BaseRemoteDataSource
import app.te.protein_chef.domain.my_orders.entity.order_details.requests.CancelOrderRequest
import app.te.protein_chef.domain.my_orders.entity.order_details.requests.FreezeOrderRequest
import javax.inject.Inject

class MyOrdersDataSource @Inject constructor(private val apiService: MyOrdersServices) :
  BaseRemoteDataSource() {

  suspend fun myOrders(pageIndex: Int) = safeApiCall {
    apiService.myOrders(pageIndex)
  }

  suspend fun myPreviousOrders(pageIndex: Int) = safeApiCall {
    apiService.myPreviousOrders(pageIndex)
  }

  suspend fun orderDetails(orderId: Int) = safeApiCall {
    apiService.orderDetails(orderId)
  }

  suspend fun orderMealsByCategory(orderId: Int, categoryId: Int) = safeApiCall {
    apiService.orderMealsByCategoryId(orderId, categoryId)
  }

  suspend fun cancelOrder(cancelOrderRequest: CancelOrderRequest) = safeApiCall {
    apiService.cancelOrder(cancelOrderRequest)
  }

  suspend fun orderDays(orderId: Int) = safeApiCall {
    apiService.orderDays(orderId)
  }

  suspend fun freezeOrder(freezeOrderRequest: FreezeOrderRequest) = safeApiCall {
    apiService.freezeOrder(freezeOrderRequest)
  }

}