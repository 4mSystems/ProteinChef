package app.te.protein_chef.domain.my_orders.use_case

import android.util.Log
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
import kotlin.math.log


class PreviousOrdersUseCase @Inject constructor(
  private val myOrdersRepository: MyOrdersRepository
) {

  //  operator fun invoke() = flow {
//    emit(Resource.Loading)
//    val result = myOrdersRepository.getMyPreviousOrders()
//    val items = mutableListOf<MyOrdersUiState>()
//    if (result is Resource.Success) {
//      val data = result.value.data.myOrdersData
//      if (data.isNullOrEmpty()) {
//        items.add(MyOrdersEmptyUiState())
//        emit(Resource.Success(items))
//      } else {
//        data.map { orderData ->
//          items.add(MyOrderDataUiState(orderData))
//        }
//        emit(Resource.Success(items))
//      }
//
//    } else
//      emit(result)
//  }.flowOn(Dispatchers.IO)

  operator fun invoke(coroutineScope: CoroutineScope) =
    Pager(
      config = PagingConfig(pageSize = 10, enablePlaceholders = false),
      pagingSourceFactory = {
        object : AbstractPagingSource<MyOrdersUiState>() {

          override suspend fun fetchData(
            pageSize: Int,
            PageIndex: Int
          ): List<MyOrdersUiState> {
            val result = myOrdersRepository.getMyPreviousOrders(PageIndex)
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
            Log.e("fetchData", "fetchData: " + items.size)
            return items
          }

          override fun hasNextPages(): Boolean {
            return nextPage != null
          }
        }.getPagingSource()
      }
    ).flow.flowOn(Dispatchers.IO).cachedIn(coroutineScope)


}