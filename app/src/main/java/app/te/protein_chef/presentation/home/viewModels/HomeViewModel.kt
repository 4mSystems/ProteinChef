package app.te.protein_chef.presentation.home.viewModels

import androidx.lifecycle.viewModelScope
import app.te.protein_chef.domain.home.models.HomeMainData
import app.te.protein_chef.domain.home.use_case.HomeUseCase
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val homeUseCase: HomeUseCase
) : BaseViewModel() {
  private val _homeResponse =
    MutableStateFlow<Resource<BaseResponse<HomeMainData>>>(Resource.Default)
  val homeResponse = _homeResponse

  init {
    getHomeData()
  }

  private fun getHomeData() {
    homeUseCase.homeService(0.0, 0.0)
      .onEach { result ->
        _homeResponse.value = result
      }
      .launchIn(viewModelScope)

  }


}