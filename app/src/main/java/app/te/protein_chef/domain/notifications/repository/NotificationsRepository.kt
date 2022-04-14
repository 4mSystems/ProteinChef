package app.te.protein_chef.domain.notifications.repository

import app.te.protein_chef.domain.notifications.entity.NotificationPaginate
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource

interface NotificationsRepository {
  suspend fun getNotifications(
    pageSize: Int,
    pageIndex: Int
  ): Resource<BaseResponse<NotificationPaginate>>
}