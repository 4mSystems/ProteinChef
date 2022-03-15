package app.grand.tafwak.presentation.account.ui_state

import android.content.Context
import app.grand.tafwak.presentation.account.AccountAdapter
import app.grand.tafwak.presentation.account.AccountFragmentDirections
import com.structure.base_mvvm.R

class AccountUiState {
  lateinit var accountAdapter: AccountAdapter
  lateinit var context: Context
  fun updateAccountList() {
    val accountItems = ArrayList<AccountItem>().also { list ->
      list.add(
        AccountItem(
          context.getString(R.string.profile),
          AccountFragmentDirections.actionAccountFragmentToProfileFragment()
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