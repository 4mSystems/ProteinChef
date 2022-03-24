package app.te.protein_chef.presentation.make_order.viewModels

import app.te.protein_chef.domain.account.use_case.UserLocalUseCase
import app.te.protein_chef.presentation.base.BaseViewModel
import com.structure.base_mvvm.DefaultLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DetectDeliveryViewModel @Inject constructor(
  private val userLocalUseCase: UserLocalUseCase
) : BaseViewModel() {

  suspend fun getDefaultLocation(): Flow<DefaultLocation> =
    flow {
      userLocalUseCase.getDefaultLocation()
        .collect { defaultLocation ->
          emit(defaultLocation)
        }
    }

}