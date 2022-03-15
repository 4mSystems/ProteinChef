package app.grand.tafwak.presentation.home.ui_state

import app.grand.tafwak.domain.home.models.HomeMainData

class HomeUiState(val homeMainData: HomeMainData) {
  val onZone = homeMainData.on_zone
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