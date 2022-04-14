package app.te.protein_chef.domain.my_orders.repository

import app.te.protein_chef.domain.my_orders.entity.MyOrdersPaginate
import app.te.protein_chef.domain.my_orders.entity.order_details.OrderDetails
import app.te.protein_chef.domain.my_orders.entity.order_details.requests.CancelOrderRequest
import app.te.protein_chef.domain.my_orders.entity.order_details.requests.FreezeOrderRequest
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource

interface MyOrdersRepository {
  suspend fun getMyOrders(pageIndex: Int): Resource<BaseResponse<MyOrdersPaginate>>
  suspend fun getMyPreviousOrders(pageIndex: Int): Resource<BaseResponse<MyOrdersPaginate>>
  suspend fun getOrderDetails(orderId: Int): Resource<BaseResponse<OrderDetails>>
  suspend fun getOrderMealsByCategoryId(
    orderId: Int,
    categoryId: Int
  ): Resource<BaseResponse<OrderDetails>>

  suspend fun cancelOrder(cancelOrderRequest: CancelOrderRequest): Resource<BaseResponse<*>>
  suspend fun orderDays(orderId: Int): Resource<BaseResponse<Array<String>>>
  suspend fun freezeOrder(freezeOrderRequest: FreezeOrderRequest): Resource<BaseResponse<*>>

}