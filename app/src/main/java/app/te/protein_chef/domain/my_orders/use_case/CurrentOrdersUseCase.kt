package app.te.protein_chef.domain.my_orders.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import app.te.protein_chef.data.remote.AbstractPagingSource
import app.te.protein_chef.domain.my_orders.repository.MyOrdersRepository
import app.te.protein_chef.domain.utils.*
import app.te.protein_chef.presentation.my_orders.ui_state.MyOrderDataUiState
import app.te.protein_chef.presentation.my_orders.ui_state.MyOrdersEmptyUiState
import app.te.protein_chef.presentation.my_orders.ui_state.MyOrdersUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class CurrentOrdersUseCase @Inject constructor(
  private val myOrdersRepository: MyOrdersRepository
) {
  operator fun invoke(coroutineScope: CoroutineScope) =
    Pager(
      config = PagingConfig(pageSize = 10, enablePlaceholders = false),
      pagingSourceFactory = {
        object : AbstractPagingSource<MyOrdersUiState>() {

          override suspend fun fetchData(
            pageSize: Int,
            PageIndex: Int
          ): List<MyOrdersUiState> {
            val result = myOrdersRepository.getMyOrders(PageIndex)
            val items = mutableListOf<MyOrdersUiState>()
            if (result is Resource.Success) {
              nextPage = result.value.data.links?.next
              val data = result.value.data.myOrdersData
              if (data.isEmpty()) {
                items.add(MyOrdersEmptyUiState())
              } else {
                data.map { orderData ->
                  items.add(MyOrderDataUiState(orderData))
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
    ).flow.flowOn(Dispatchers.IO).cachedIn(coroutineScope)


}