package app.te.protein_chef.presentation.home.ui_state

import app.te.protein_chef.domain.home.models.Slider

data class SliderUiItemState(val slider: Slider) {
  fun getSliderImage(): String = slider.image
  fun getSliderUrl(): String = slider.url
}