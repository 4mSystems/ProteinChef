package app.te.protein_chef.data.my_order.data_source

import app.te.protein_chef.data.remote.BaseRemoteDataSource
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
    apiService.orderMealsByCategoryId(orderId,categoryId)
  }

}