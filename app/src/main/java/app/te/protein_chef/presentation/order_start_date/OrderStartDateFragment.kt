package app.te.protein_chef.presentation.order_start_date

import androidx.fragment.app.viewModels
import app.te.protein_chef.R
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.navigateSafe
import app.te.protein_chef.presentation.base.utils.getSelectedDate
import app.te.protein_chef.presentation.packages.viewModel.PackagesViewModel
import app.te.protein_chef.databinding.FragmentOrderStartDateBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class OrderStartDateFragment : BaseFragment<FragmentOrderStartDateBinding>(),
  OrderStartDateEventListener {
  private var selectedDate = ""
  private val viewModel: PackagesViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_order_start_date
  override fun setBindingVariables() {
    binding.eventListener = this

  }

  override fun setUpViews() {
    setupCalendar()

  }

  private fun setupCalendar() {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, 2)
    binding.calendar.minDate = calendar.timeInMillis
    selectedDate = binding.calendar.getSelectedDate()
    binding.calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
      selectedDate = year.toString().plus("-").plus((month + 1)).plus("-").plus(dayOfMonth)
    }
  }

  override fun openMeals() {
    navigateSafe(
      OrderStartDateFragmentDirections.actionOrderStartDateFragmentToMealsFragment(
        OrderStartDateFragmentArgs.fromSavedStateHandle(viewModel.savedStateHandle).categoryId,
        OrderStartDateFragmentArgs.fromSavedStateHandle(viewModel.savedStateHandle).title,
        selectedDate
      )
    )
  }
}