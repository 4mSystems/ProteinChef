package app.te.protein_chef.presentation.home.ui_state

import android.view.View
import app.te.protein_chef.domain.home.models.HomeMainData

class HomeUiState(val homeMainData: HomeMainData) {

  val onZone = View.GONE.takeIf { homeMainData.on_zone } ?: View.VISIBLE

  fun setUpSlider(): List<SliderUiItemState> {
    return homeMainData.sliders.map { SliderUiItemState(it) }
  }

  fun setUpPackages(): List<PackagesUiItemState> {
    return homeMainData.packages.map { PackagesUiItemState(it) }
  }

  fun setUpOffers(): List<OffersUiItemState> {
    return homeMainData.offers.map { OffersUiItemState(it) }
  }

}