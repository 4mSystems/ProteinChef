package app.te.protein_chef.presentation.my_coupons.ui_state

import android.content.Context
import android.view.View
import app.te.protein_chef.presentation.my_coupons.MyCouponsEventListener

interface MyCouponsUiState {
  fun getLayoutRes(): Int
  fun bind(item: View?, position: Int, context: Context,myCouponsEventListener: MyCouponsEventListener)
  fun getId(): Int
}