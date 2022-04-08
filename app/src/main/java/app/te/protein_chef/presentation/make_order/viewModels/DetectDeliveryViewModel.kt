package app.te.protein_chef.presentation.make_order.viewModels

import androidx.lifecycle.viewModelScope
import app.te.protein_chef.domain.account.use_case.UserLocalUseCase
import app.te.protein_chef.domain.settings.use_case.OrderMainSettingsUseCase
import app.te.protein_chef.presentation.base.BaseViewModel
import com.structure.base_mvvm.DefaultLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetectDeliveryViewModel @Inject constructor(
  private val userLocalUseCase: UserLocalUseCase,
  private val orderSettingUseCase: OrderMainSettingsUseCase
) : BaseViewModel() {

  suspend fun getDefaultLocation(): Flow<DefaultLocation> =
    flow {
      userLocalUseCase.getDefaultLocation()
        .collect { defaultLocation ->
          emit(defaultLocation)
        }
    }

  suspend fun getShippingValue() =
    flow {
      userLocalUseCase.getShippingValue()
        .collect { shippingValue ->
          emit(shippingValue)
        }
    }

  suspend fun getWorkingHours() =
    flow {
      userLocalUseCase.getWorkingHours()
        .collect { workingHours ->
          emit(workingHours)
        }
    }

  fun orderSettings() {
    viewModelScope.launch {
      orderSettingUseCase.invoke()
        .collect {}
    }
  }
}