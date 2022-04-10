package app.te.protein_chef.data.my_order.repository

import app.te.protein_chef.data.my_order.data_source.MyOrdersDataSource
import app.te.protein_chef.domain.my_orders.entity.MyOrdersPaginate
import app.te.protein_chef.domain.my_orders.entity.order_details.OrderDetails
import app.te.protein_chef.domain.my_orders.repository.MyOrdersRepository
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import javax.inject.Inject

class MyOrdersRepositoryImpl @Inject constructor(
  private val remoteDataSource: MyOrdersDataSource
) : MyOrdersRepository {
  override suspend fun getMyOrders(pageIndex: Int): Resource<BaseResponse<MyOrdersPaginate>> =
    remoteDataSource.myOrders(pageIndex)

  override suspend fun getMyPreviousOrders(pageIndex: Int): Resource<BaseResponse<MyOrdersPaginate>> =
    remoteDataSource.myPreviousOrders(pageIndex)

  override suspend fun getOrderDetails(orderId: Int): Resource<BaseResponse<OrderDetails>> =
    remoteDataSource.orderDetails(orderId)

  override suspend fun getOrderMealsByCategoryId(
    orderId: Int,
    categoryId: Int
  ): Resource<BaseResponse<OrderDetails>> =
    remoteDataSource.orderMealsByCategory(orderId, categoryId)


}