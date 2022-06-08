package app.te.protein_chef.presentation.packages.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import app.te.protein_chef.domain.packages_categories.use_case.PackageCategoriesUseCase
import app.te.protein_chef.domain.packages_categories.use_case.PackageSubCategoriesUseCase
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PackagesViewModel @Inject constructor(
  private val packageCategoriesUseCase: PackageCategoriesUseCase,
  private val packageSubCategoriesUseCase: PackageSubCategoriesUseCase,
  val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

  private val _typesResponse =
    MutableStateFlow<Resource<Any>>(Resource.Default)
  val typesResponse = _typesResponse
  private val _menuResponse =
    MutableStateFlow<Resource<Any>>(Resource.Default)
  val menuResponse = _menuResponse

//  init {
//    savedStateHandle.get<Int>("package_id")?.let { package_id ->
//      getPackageCategories(package_id)
//    }
//
//  }

  fun getPackageCategories(package_id: Int) {
    packageCategoriesUseCase.invoke(package_id)
      .onEach { result ->
        _typesResponse.value = result
      }
      .launchIn(viewModelScope)

  }

  fun getSubCategory(package_type_id: Int, package_id: Int) {
    packageSubCategoriesUseCase.invoke(package_type_id, package_id)
      .onEach { result ->
        _typesResponse.value = result
      }
      .launchIn(viewModelScope)

  }

  fun getCategoryMenu(category_id: Int) {
    packageCategoriesUseCase.getCategoryMenu(category_id)
      .onEach { result ->
        _menuResponse.value = result
      }
      .launchIn(viewModelScope)

  }

}