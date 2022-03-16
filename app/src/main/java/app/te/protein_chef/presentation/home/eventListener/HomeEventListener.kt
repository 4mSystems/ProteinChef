package app.te.protein_chef.presentation.home.eventListener

interface HomeEventListener {
  fun openSliderUrl()
  fun openPackageDetails(packageId: Int, title: String)
}