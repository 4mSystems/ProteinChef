package app.te.protein_chef.presentation.more

import android.content.Context
import app.te.protein_chef.R

class MoreUiState {
  lateinit var moreAdapter: MoreAdapter
  lateinit var context: Context

  fun updateMoreList() {
    val moreItems = ArrayList<MoreItem>().also { list ->
      list.add(
        MoreItem(
          context.getString(R.string.about),
          MoreFragmentDirections.actionMoreFragmentToAboutFragment(),
          R.id.contact_fragment
        )
      )
      list.add(
        MoreItem(
          context.getString(R.string.terms),
          MoreFragmentDirections.actionMoreFragmentToTermsFragment(),
          R.id.terms_fragment
        )
      )
      list.add(
        MoreItem(
          context.getString(R.string.lang),
          MoreFragmentDirections.actionMoreFragmentToLanguageFragment2(),
          R.id.languageFragment
        )
      )
      list.add(
        MoreItem(
          context.getString(R.string.support),
          MoreFragmentDirections.actionMoreFragmentToWebViewFragment(),
          R.id.webViewFragment
        )
      )

      list.add(
        MoreItem(
          context.getString(R.string.privacy),
          MoreFragmentDirections.actionMoreFragmentToPrivacyFragment(),
          R.id.privacy_fragment
        )
      )

      list.add(
        MoreItem(
          context.getString(R.string.share_app),
          MoreFragmentDirections.actionMoreFragmentToPrivacyFragment(),
          0
        )
      )
      list.add(
        MoreItem(
          context.getString(R.string.rate_app),
          MoreFragmentDirections.actionMoreFragmentToPrivacyFragment(),
          1
        )
      )

    }
    moreAdapter.differ.submitList(moreItems)
  }

}