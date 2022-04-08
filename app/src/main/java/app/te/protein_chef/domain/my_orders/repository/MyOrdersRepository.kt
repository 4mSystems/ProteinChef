package app.te.protein_chef.domain.my_orders.repository

import app.te.protein_chef.domain.my_orders.entity.MyOrdersPaginate
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource

interface MyOrdersRepository {
  suspend fun getMyOrders(pageIndex :Int): Resource<BaseResponse<MyOrdersPaginate>>
  suspend fun getMyPreviousOrders(pageIndex :Int): Resource<BaseResponse<MyOrdersPaginate>>
}