package app.te.protein_chef.domain.notifications.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import app.te.protein_chef.data.remote.AbstractPagingSource
import app.te.protein_chef.domain.notifications.repository.NotificationsRepository
import app.te.protein_chef.domain.utils.*
import app.te.protein_chef.presentation.notifications.ui_state.NotificationsDataUiState
import app.te.protein_chef.presentation.notifications.ui_state.NotificationsEmptyUiState
import app.te.protein_chef.presentation.notifications.ui_state.NotificationsUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class NotificationsUseCase @Inject constructor(
  private val notificationsRepository: NotificationsRepository
) {
  operator fun invoke() =
    Pager(
      config = PagingConfig(pageSize = 10, enablePlaceholders = false),
      pagingSourceFactory = {
        object : AbstractPagingSource<NotificationsUiState>() {

          override suspend fun fetchData(
            pageSize: Int,
            PageIndex: Int
          ): List<NotificationsUiState> {
            val result = notificationsRepository.getNotifications(pageSize, PageIndex)
            val items = mutableListOf<NotificationsUiState>()
            if (result is Resource.Success) {
              nextPage = result.value.data.links?.next
              val data = result.value.data.notificationData
              if (data.isNullOrEmpty()) {
                items.add(NotificationsEmptyUiState())
              } else {
                data.map { orderData ->
                  items.add(NotificationsDataUiState(orderData))
                }
              }
            }
            return items
          }

          override fun hasNextPages(): Boolean {
            return nextPage != null
          }
        }.getPagingSource()
      }
    ).flow.flowOn(Dispatchers.IO)


}