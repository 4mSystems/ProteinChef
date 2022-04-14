package app.te.protein_chef.data.notifications.data_source

import app.te.protein_chef.domain.notifications.entity.NotificationPaginate
import app.te.protein_chef.domain.utils.BaseResponse
import retrofit2.http.*

interface NotificationsServices {

  @GET("V1/user/notifications/{page_size}")
  suspend fun getNotifications(
    @Path("page_size") pageSize: Int,
    @Query("page") pageIndex: Int
  ): BaseResponse<NotificationPaginate>


}