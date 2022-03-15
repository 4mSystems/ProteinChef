package app.grand.tafwak.presentation.home.ui_state

import app.grand.tafwak.domain.home.models.Packages

data class PackagesUiItemState(val packages: Packages) {
  fun getPackageId(): Int = packages.id
  fun getPackageImage(): String = packages.image
  fun getPackageName(): String = packages.title
}