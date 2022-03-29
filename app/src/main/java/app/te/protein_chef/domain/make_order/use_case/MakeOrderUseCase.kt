package app.te.protein_chef.domain.make_order.use_case

import app.te.protein_chef.domain.make_order.entity.MakeOrderRequest
import app.te.protein_chef.domain.make_order.repository.MakeOrderRepository
import app.te.protein_chef.domain.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class MakeOrderUseCase @Inject constructor(
  private val makeOrderRepository: MakeOrderRepository
) {

  operator fun invoke(
    makeOrderRequest: MakeOrderRequest
  ) = flow {
    emit(Resource.Loading)
    val result = makeOrderRepository.makeOrder(makeOrderRequest)
    emit(result)
  }.flowOn(Dispatchers.IO)


}