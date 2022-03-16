package app.te.protein_chef.presentation.my_coupons.ui_state

import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import app.te.protein_chef.presentation.my_coupons.MyCouponsEventListener
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.ItemEmptyCouponBinding

class MyCouponsEmptyUiState() : MyCouponsUiState {
  override fun getLayoutRes(): Int = R.layout.item_empty_coupon

  override fun bind(item: View?, position: Int, context: Context,myCouponsEventListener: MyCouponsEventListener) {
    item ?: return
    val binding = DataBindingUtil.bind<ItemEmptyCouponBinding>(item)
  }

  override fun getId(): Int = 0
}