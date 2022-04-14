package app.te.protein_chef.data.notifications.repository

import app.te.protein_chef.data.notifications.data_source.NotificationsDataSource
import app.te.protein_chef.domain.notifications.entity.NotificationPaginate
import app.te.protein_chef.domain.notifications.repository.NotificationsRepository
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import javax.inject.Inject

class NotificationsRepositoryImpl @Inject constructor(
  private val remoteDataSource: NotificationsDataSource
) : NotificationsRepository {
  override suspend fun getNotifications(
    pageSize: Int,
    pageIndex: Int
  ): Resource<BaseResponse<NotificationPaginate>> =
    remoteDataSource.getNotifications(pageSize, pageIndex)

}