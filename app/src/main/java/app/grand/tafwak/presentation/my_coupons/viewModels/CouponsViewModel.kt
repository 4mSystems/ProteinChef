package app.grand.tafwak.presentation.my_coupons.viewModels

import androidx.lifecycle.viewModelScope
import app.grand.tafwak.domain.my_coupons.use_case.MyCouponsUseCase
import app.grand.tafwak.domain.utils.Resource
import app.grand.tafwak.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CouponsViewModel @Inject constructor(
  private val myCouponsUseCase: MyCouponsUseCase
) : BaseViewModel() {

  private val _couponsResponse =
    MutableStateFlow<Resource<Any>>(Resource.Default)
  val couponsResponse = _couponsResponse
  init {
    getMyCoupons()
  }

  private fun getMyCoupons() {
    myCouponsUseCase.invoke()
      .onEach { result ->
        _couponsResponse.value = result
      }
      .launchIn(viewModelScope)

  }

}