package app.te.protein_chef.presentation.base

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import app.te.protein_chef.R
import app.te.protein_chef.presentation.base.utils.*
import java.util.*

class DatePickerFragment : DialogFragment() {
  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
     val args= DatePickerFragmentArgs.fromBundle(requireArguments())
    val datePickerDialog = DatePickerDialog(
      requireActivity(), R.style.date_picker_theme, { _, year, month, dayOfMonth ->
        val monthDate = month.toString().takeIf { it.length == 2 } ?: "0".plus(month)
        val dayDate = dayOfMonth.toString().takeIf { it.length == 2 } ?: "0".plus(dayOfMonth)
        val bundle = Bundle()
        bundle.putString(
          Constants.SELECTED_DATE, year.toString().plus("-").plus(monthDate).plus("-").plus(dayDate)
        )
        setFragmentResult(Constants.BUNDLE, bundle)
      }, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH
    )
    datePickerDialog.datePicker.minDate = minDate(args.startDate)

    return datePickerDialog
  }

}