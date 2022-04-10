package app.te.protein_chef.data.my_order.data_source

import app.te.protein_chef.domain.my_orders.entity.MyOrdersPaginate
import app.te.protein_chef.domain.my_orders.entity.order_details.OrderDetails
import app.te.protein_chef.domain.utils.BaseResponse
import retrofit2.http.*

interface MyOrdersServices {

  @GET("V1/user/recent-subscribes")
  suspend fun myOrders(@Query("page") pageIndex: Int): BaseResponse<MyOrdersPaginate>

  @GET("V1/user/previous-subscribes")
  suspend fun myPreviousOrders(@Query("page") pageIndex: Int): BaseResponse<MyOrdersPaginate>

  @GET("V1/user/order-details/{order_id}")
  suspend fun orderDetails(@Path("order_id") order_id: Int): BaseResponse<OrderDetails>

  @GET("V1/user/order-details/{order_id}/{category_id}")
  suspend fun orderMealsByCategoryId(
    @Path("order_id") order_id: Int,
    @Path("category_id") category_id: Int
  ): BaseResponse<OrderDetails>


}