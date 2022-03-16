package app.te.protein_chef.presentation.my_coupons.viewModels

import androidx.lifecycle.viewModelScope
import app.te.protein_chef.domain.my_coupons.use_case.MyCouponsUseCase
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.BaseViewModel
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