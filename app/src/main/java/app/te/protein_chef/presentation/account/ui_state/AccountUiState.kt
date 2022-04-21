package app.te.protein_chef.presentation.account.ui_state

import android.content.Context
import app.te.protein_chef.presentation.account.AccountAdapter
import app.te.protein_chef.presentation.account.AccountFragmentDirections
import app.te.protein_chef.R
import app.te.protein_chef.domain.profile.entity.UpdateProfileRequest

class AccountUiState {
  lateinit var accountAdapter: AccountAdapter
  lateinit var context: Context
  fun updateAccountList() {
    val accountItems = ArrayList<AccountItem>().also { list ->
      list.add(
        AccountItem(
          context.getString(R.string.profile),
          AccountFragmentDirections.actionAccountFragmentToProfileFragment(UpdateProfileRequest())
        )
      )
      list.add(
        AccountItem(
          context.getString(R.string.my_locations),
          AccountFragmentDirections.actionAccountFragmentToMyLocationsFragment()
        )
      )
      list.add(
        AccountItem(
          context.getString(R.string.promo_codes),
          AccountFragmentDirections.actionAccountFragmentToCouponsFragment()
        )
      )
      list.add(
        AccountItem(
          context.getString(R.string.log_out),
          null
        )
      )

    }
    accountAdapter.differ.submitList(accountItems)
  }

}