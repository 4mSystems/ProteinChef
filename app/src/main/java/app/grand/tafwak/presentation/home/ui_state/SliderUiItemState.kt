package app.grand.tafwak.presentation.home.ui_state

import app.grand.tafwak.domain.home.models.Slider

data class SliderUiItemState(val slider: Slider) {
  fun getSliderImage(): String = slider.image
  fun getSliderUrl(): String = slider.url
}