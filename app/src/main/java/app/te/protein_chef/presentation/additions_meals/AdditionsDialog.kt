package app.te.protein_chef.presentation.additions_meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import app.te.protein_chef.R
import app.te.protein_chef.databinding.AdditionsDialogBinding
import app.te.protein_chef.presentation.additions_meals.event_listener.AdditionalEventListener
import app.te.protein_chef.presentation.base.extensions.navigateSafe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdditionsDialog : BottomSheetDialogFragment(), AdditionalEventListener {
  lateinit var binding: AdditionsDialogBinding
  private val additionsDialogArgs: AdditionsDialogArgs by navArgs()
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding =
      DataBindingUtil.inflate(inflater, R.layout.additions_dialog, container, false)
    binding.eventListener = this
    return binding.root
  }

  override fun openAdditional() {
    dismiss()
    navigateSafe(
      AdditionsDialogDirections.actionAdditionsDialogToAdditionsMealsFragment(
        additionsDialogArgs.orderRequest
      )
    )
  }

  override fun openLocations() {

  }

}