package app.te.protein_chef.presentation.order_details

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.protein_chef.R
import app.te.protein_chef.databinding.FragmentOrderDetailsBinding
import app.te.protein_chef.domain.my_orders.entity.order_details.OrderMeal
import app.te.protein_chef.domain.packages_categories.entity.category_menu.CategoryMenu
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.handleApiError
import app.te.protein_chef.presentation.base.extensions.hideKeyboard
import app.te.protein_chef.presentation.base.extensions.setUpAdapter
import app.te.protein_chef.presentation.order_details.adapters.OrderCategoriesAdapter
import app.te.protein_chef.presentation.order_details.adapters.OrderMealsAdapter
import app.te.protein_chef.presentation.order_details.listeners.OrderDetailsListeners
import app.te.protein_chef.presentation.order_details.ui_state.OrderDetailsUiState
import app.te.protein_chef.presentation.order_details.viewModels.OrderDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class OrderDetailsFragment : BaseFragment<FragmentOrderDetailsBinding>(), OrderDetailsListeners {
  private val viewModel: OrderDetailsViewModel by viewModels()

  private val adapter = OrderCategoriesAdapter(this)
  private val orderMealAdapter = OrderMealsAdapter()
  private var orderDetailsUiState = OrderDetailsUiState()

  override
  fun getLayoutId() = R.layout.fragment_order_details

  override fun setBindingVariables() {
    viewModel.orderDetails()
  }

  override fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.orderDetailsResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            orderDetailsUiState = it.value as OrderDetailsUiState
            bindUi()
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }
    lifecycleScope.launchWhenResumed {
      viewModel.orderMealResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            val data = it.value as OrderDetailsUiState
            updateOrderMealsAdapter(it.value.order_meals)
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }

  }

  private fun bindUi() {
    binding.uiState = orderDetailsUiState
    // update categories adapter
    adapter.differ.submitList(orderDetailsUiState.getMealCategories())
    binding.mainMeals.rcMainMeals.setUpAdapter(adapter, "1", "2")
    updateOrderMealsAdapter(orderDetailsUiState.order_meals)
  }

  private fun updateOrderMealsAdapter(mealTypes: List<OrderMeal>) {
    orderMealAdapter.differ.submitList(orderDetailsUiState.getOrderMealsItemUiState(mealTypes))
    binding.rcMeals.setUpAdapter(orderMealAdapter, "1", "2")
  }

  override fun changeCategoryType(categoryId: Int) {
    viewModel.orderMealsBuCategory(categoryId)
  }
}