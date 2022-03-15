package app.grand.tafwak.presentation.order_start_date

import android.util.Log
import androidx.navigation.fragment.navArgs
import com.structure.base_mvvm.R
import app.grand.tafwak.presentation.base.BaseFragment
import app.grand.tafwak.presentation.base.extensions.navigateSafe
import app.grand.tafwak.presentation.base.utils.getSelectedDate
import com.structure.base_mvvm.databinding.FragmentOrderStartDateBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class OrderStartDateFragment : BaseFragment<FragmentOrderStartDateBinding>(),
  OrderStartDateEventListener {
  private val args: OrderStartDateFragmentArgs by navArgs()
  private var selectedDate = ""

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
      Log.e("getSelectedDate", "getSelectedDate: " + selectedDate)
    }
  }

  override fun openMeals() {
    navigateSafe(
      OrderStartDateFragmentDirections.actionOrderStartDateFragmentToMealsFragment(
        args.categoryId,
        args.title,
        selectedDate
      )
    )
  }
}