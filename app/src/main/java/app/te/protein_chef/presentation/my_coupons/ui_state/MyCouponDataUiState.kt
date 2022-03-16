package app.te.protein_chef.presentation.my_coupons.ui_state

import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import app.te.protein_chef.domain.my_coupons.entity.MyCouponsDto
import app.te.protein_chef.presentation.my_coupons.MyCouponsEventListener
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.ItemMyCouponBinding

class MyCouponDataUiState(private val myCouponsDto: MyCouponsDto) : MyCouponsUiState {
  override fun getLayoutRes(): Int = R.layout.item_my_coupon

  override fun bind(
    item: View?,
    position: Int,
    context: Context,
    myCouponsEventListener: MyCouponsEventListener
  ) {
    item ?: return
    val binding = DataBindingUtil.bind<ItemMyCouponBinding>(item)
    binding?.uiState = this
    binding?.coupnEventListener = myCouponsEventListener
  }

  override fun getId(): Int = myCouponsDto.id
  fun getTitle(): String = myCouponsDto.title
}