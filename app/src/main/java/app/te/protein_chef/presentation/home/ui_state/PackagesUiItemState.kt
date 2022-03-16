package app.te.protein_chef.presentation.home.ui_state

import app.te.protein_chef.domain.home.models.Packages

data class PackagesUiItemState(val packages: Packages) {
  fun getPackageId(): Int = packages.id
  fun getPackageImage(): String = packages.image
  fun getPackageName(): String = packages.title
}