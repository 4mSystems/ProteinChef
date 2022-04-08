package app.te.protein_chef.data.my_order.data_source

import app.te.protein_chef.domain.my_orders.entity.MyOrdersPaginate
import app.te.protein_chef.domain.utils.BaseResponse
import retrofit2.http.*

interface MyOrdersServices {

  @GET("V1/user/recent-subscribes")
  suspend fun myOrders(@Query("page") pageIndex: Int): BaseResponse<MyOrdersPaginate>

  @GET("V1/user/previous-subscribes")
  suspend fun myPreviousOrders(@Query("page") pageIndex: Int): BaseResponse<MyOrdersPaginate>


}