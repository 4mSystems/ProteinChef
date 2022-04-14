package app.te.protein_chef.data.notifications.data_source

import app.te.protein_chef.data.remote.BaseRemoteDataSource
import app.te.protein_chef.domain.my_orders.entity.order_details.requests.CancelOrderRequest
import app.te.protein_chef.domain.my_orders.entity.order_details.requests.FreezeOrderRequest
import javax.inject.Inject

class NotificationsDataSource @Inject constructor(private val apiService: NotificationsServices) :
  BaseRemoteDataSource() {

  suspend fun getNotifications(pageSize: Int, pageIndex: Int) = safeApiCall {
    apiService.getNotifications(pageSize, pageIndex)
  }

}